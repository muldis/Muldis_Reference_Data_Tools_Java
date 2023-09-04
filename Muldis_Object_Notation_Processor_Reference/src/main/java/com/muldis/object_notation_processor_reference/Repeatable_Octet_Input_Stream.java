package com.muldis.object_notation_processor_reference;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Repeatable_Octet_Input_Stream
{
    // A Repeatable_Octet_Input_Stream object is an octet input stream which
    // supports repeatable-reads to simplify look-ahead operations by its users.
    // It is implemented as a wrapper around a standard Java system-defined
    // "InputStream" object which is its own source of octets.
    // This class assumes that its own source of octets does not support
    // repeatable-reads, so it implements a memory to support such itself.
    // By default, a user pulls a single octet at a time from the stream
    // and they can not pull the same octet again later as this stream has
    // then forgotten about it.

    // The same convention of the underlying source stream is used such
    // that an operation that would normally return a single octet as an
    // integer in 0..255 would instead return -1 when there aren't any more
    // octets, rather than some error being raised.

    // Users are required to ask for repeatable-read support by requesting
    // a Repeatable_Octet_Input_Stream_Position_Hold object to hold a place for them
    // in the stream, and then they can repeatedly read any octets later
    // than that position in the stream, until this hold is released.
    // The memory will only hold a copy of octets since the earliest hold
    // in the stream; releasing the oldest hold causes the memory to forget
    // the octets between then and the next oldest hold.
    // An example look-ahead scenario is to try treating the input as
    // several different data formats until one is found that works.

    // Each individual octet is represented in terms of a signed
    // 32-bit integer whose value is one of 0..255 rather than as an
    // unsigned 8-bit integer like an octet conceptually is.
    // While this has the disadvantage of increased memory use, it has the
    // advantage of greater performance for multiple reasons.
    // The standard system-provided InputStream.read()
    // provides each octet as such, and many actual processing operations
    // work in terms of standard integer math and may yield larger integers
    // and so we avoid a lot of conversion overhead.
    // Also, expected typical use cases wouldn't have more than a few
    // thousand octets lookahead so actual extra memory use should be tiny.

    // This is the first of 2 consecutive parts of the
    // Repeatable_Octet_Input_Stream that a user can still pull octets from.
    // It usually corresponds to octets that the user already read at least
    // once and that we continue to remember because they have a still
    // existing hold that was placed before they were first returned.
    // It can be repeatedly-read from.
    // This part is empty / has zero octets when no holds exist.
    // For performance reasons an "empty" list is represented by
    // a Java "null" rather than as an "empty" List object.
    private List<Integer> part_1_memory;

    // This is the second of 2 consecutive parts of the
    // Repeatable_Octet_Input_Stream that a user can still pull octets from.
    // It usually corresponds to octets that the user had never read even
    // once and so are still available even without there being any holds.
    // This is our own source of octets.
    // We assume it can't be repeatedly-read from.
    // This part is empty / has zero octets once the source is exhausted.
    // For performance reasons an "empty" list is represented by
    // a Java "null" rather than as a drained InputStream object;
    // this will be set to null when InputStream.read() returns -1.
    private InputStream part_2_source;

    // Count of octets we pulled from part_2_source since this
    // Repeatable_Octet_Input_Stream was created.
    // We still increment this even if a pulled octet isn't returned to
    // the user for some reason.
    // We use this to make sense of the position remembered by the holds.
    private int part_2_source_position;

    // This is a map of extant stream position hold objects to a historical
    // part_2_source_position integer value that each one represents.
    // Note that each of part_1_memory and stream_position_holds
    // only is valued when the other is, only is null when the other is.
    private Map<Repeatable_Octet_Input_Stream_Position_Hold, Integer>
        stream_position_holds;

    // Count in 0..part_1_memory.size of how many of the latest octets
    // of part_1_memory should be yielded on user pulls before anything
    // new is pulled from part_2_source.
    // This tracks the user's current read position in the octet input
    // stream when they are in the midst of repeating a read.
    // It counts from the end of part_1_memory rather than from the start
    // for performance reasons that means less testing or maintenance.
    // This is zero when either the user has no repeatable-read holds or
    // they are not currently performing a repeated-read.
    private int count_of_part_1_memory_octets_to_return_first;

    public Repeatable_Octet_Input_Stream(final InputStream stream_source)
    {
        if (stream_source == null)
        {
            // While a null source is actually a valid internal state
            // meaning no more octets can be read, we throw an exception
            // here because actually passing in a null one can be assumed
            // an error by the user.
            throw new IllegalArgumentException("Arg stream_source was null.");
        }
        // If we get here and stream_source has other problems such as it
        // was already closed, we'll just detect that at read_octet() time.
        this.part_1_memory = null;
        this.part_2_source = stream_source;
        this.part_2_source_position = 0;
        this.stream_position_holds = null;
        this.count_of_part_1_memory_octets_to_return_first = 0;
    }

    public int read_octet() throws IOException
    {
        if (this.part_1_memory == null && this.part_2_source == null)
        {
            // The stream is completely exhausted, so no octet to return.
            return -1;
        }
        // If we get here, there is at least one octet to return.
        if (this.count_of_part_1_memory_octets_to_return_first > 0)
        {
            // The user is currently performing a repeated-read, so return
            // an octet from their current position in part_1_memory.
            final int p1_octet_as_int
                = this.part_1_memory.get(this.part_1_memory.size()
                    - this.count_of_part_1_memory_octets_to_return_first);
            this.count_of_part_1_memory_octets_to_return_first--;
            return p1_octet_as_int;
        }
        // If we get here, then the user is not performing a repeated-read,
        // so return an octet from part_2_source if there is one.
        if (this.part_2_source == null)
        {
            // The part_2_source was already exhausted, no octet to return.
            return -1;
        }
        // If we get here, part_2_source is not yet known to be exhausted,
        // but we'll find out once we try to read from it.
        // Note that InputStream.read()
        // returns one of 0..255 when there is another octet
        // and it returns -1 when there is none / end of stream was passed.
        final int octet_as_int = this.part_2_source.read();
        if (octet_as_int < 0)
        {
            // No more octets remain to return.
            this.part_2_source = null;
            return -1;
        }
        // If we get here, part_2_source has a new octet to return.
        this.part_2_source_position++;
        if (this.part_1_memory != null)
        {
            // At least 1 hold exists so remember this octet
            // so it can be repeatably-read.
            this.part_1_memory.add(octet_as_int);
        }
        return octet_as_int;
    }

    public Repeatable_Octet_Input_Stream_Position_Hold hold_position_here()
    {
        final Repeatable_Octet_Input_Stream_Position_Hold hold
            = new Repeatable_Octet_Input_Stream_Position_Hold(this);
        if (this.stream_position_holds == null)
        {
            this.part_1_memory = new ArrayList<>();
            this.stream_position_holds = new HashMap<>();
        }
        this.stream_position_holds.put(hold, this.part_2_source_position);
        return hold;
    }

    public void read_next_at_first_unread_position()
    {
        this.count_of_part_1_memory_octets_to_return_first = 0;
    }

    public void read_next_at_held_position(
        final Repeatable_Octet_Input_Stream_Position_Hold hold)
    {
        if (hold == null)
        {
            throw new IllegalArgumentException("Arg hold is null.");
        }
        if (hold.stream_position_held_in() != this)
        {
            throw new IllegalArgumentException(
                "Arg hold doesn't belong to this Repeatable_Octet_Input_Stream.");
        }
        if (!this.stream_position_holds.containsKey(hold))
        {
            throw new IllegalArgumentException(
                "Arg hold was previously released.");
        }
        // This hold was not yet released, so it can still be used.
        final int our_held_position
            = this.stream_position_holds.get(hold);
        this.count_of_part_1_memory_octets_to_return_first
            = this.part_2_source_position - our_held_position;
    }

    public void release_held_position(
        final Repeatable_Octet_Input_Stream_Position_Hold hold)
    {
        if (hold == null)
        {
            throw new IllegalArgumentException("Arg hold is null.");
        }
        if (hold.stream_position_held_in() != this)
        {
            throw new IllegalArgumentException(
                "Arg hold doesn't belong to this Repeatable_Octet_Input_Stream.");
        }
        if (!this.stream_position_holds.containsKey(hold))
        {
            // Position was already released before, so nothing to do now.
            return;
        }
        // This hold was not yet released, so do it now.
        final int our_held_position
            = this.stream_position_holds.remove(hold);
        if (this.stream_position_holds.isEmpty())
        {
            // This hold was the only one left, so simply empty the buffer.
            this.part_1_memory = null;
            this.stream_position_holds = null;
            if (this.count_of_part_1_memory_octets_to_return_first > 0)
            {
                // If user was currently repeat-reading from a point in the
                // stream uniquely protected by the just-removed hold,
                // they've now skipped past the no longer protected octets.
                this.count_of_part_1_memory_octets_to_return_first = 0;
            }
            return;
        }
        // If we get here, there was at least 1 other hold left.
        // TODO: ITS STILL UNCLEAR THAT THIS JUST COLLECTS THE HASHMAP
        // VALUES AND NOT THE PAIRS.
        final int earliest_other_held_position
            = Collections.min(this.part_1_memory.stream().toList());
        if (our_held_position >= earliest_other_held_position)
        {
            // This hold isn't uniquely earliest outstanding one in stream,
            // so the buffer won't be changed just due to its release.
            // Note that the newly released hold might have been equal
            // to one of the others that still remains.
            return;
        }
        // This hold is uniquely earliest one of several remaining, so
        // remove buffer contents between this one and next earliest one.
        // TODO: VERIFY THAT ALL THIS MATH IS RIGHT, THERE AREN'T OFF BY ONE ERRORS.
        final int position_adjusted_by
            = earliest_other_held_position - our_held_position;
        this.part_1_memory = this.part_1_memory.subList(
            position_adjusted_by,
            this.part_1_memory.size()
        );
        if (this.count_of_part_1_memory_octets_to_return_first
            > this.part_1_memory.size())
        {
            // If user was currently repeat-reading from a point in the
            // stream uniquely protected by the just-removed hold,
            // they've now skipped past the no longer protected octets.
            this.count_of_part_1_memory_octets_to_return_first
                = this.part_1_memory.size();
        }
    }
}
