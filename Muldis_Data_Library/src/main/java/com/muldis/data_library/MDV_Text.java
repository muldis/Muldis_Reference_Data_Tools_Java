package com.muldis.data_library;

public final class MDV_Text implements MDV_Positional<MDV_Text>
{
    private static final MDV_Text __empty = new MDV_Text("", false, 0);

    // A value of the .NET class String is immutable.
    // It should be safe to pass around without cloning.
    // Each logical member represents a single Unicode standard character
    // code point from either the Basic Multilingual Plane (BMP), which
    // is those member values in the range [0..0xD7FF,0xE000..0xFFFF],
    // or from either of the 16 supplementary planes, which is those
    // member values in the range [0x10000..0x10FFFF].
    // A .NET String is instead natively composed of a sequence of
    // .NET Char that are each in the range [0..0xFFFF],
    // such that each BMP member is represented by 1 element and each
    // non-BMP member is represented by 2 consecutive elements that are a
    // "surrogate pair" in the range [0xD800..0xDFFF].
    // Therefore, the native "length" of a .NET String only matches the
    // logical "length" of the Text when all code points are in the BMP.
    // While it is possible for a .NET String to contain an isolated
    // "surrogate" Char outside of a proper "surrogate pair", we forbid
    // such a malformed .NET String from being used.
    private final String __code_point_members_as_String;

    // This is true iff we know that at least 1 code point member is NOT
    // in the Basic Multilingual Plane (BMP); this is false iff we know
    // that there is no such code point member.  That is, with respect
    // to a .NET String, this is true iff we know the .NET String has at
    // least 1 "surrogate pair".  While this value can be derived
    // from __code_point_members_as_String, we cache it for performance.
    private final boolean __has_any_non_BMP;

    // Count of the code point members.  While this value can be derived
    // from __code_point_members_as_String, we cache it for performance.
    private final int __count_of_code_point_members;

    private MDV_Text(final String code_point_members_as_String,
        final boolean has_any_non_BMP, final int count_of_code_point_members)
    {
        this.__code_point_members_as_String = code_point_members_as_String;
        this.__has_any_non_BMP = has_any_non_BMP;
        this.__count_of_code_point_members = count_of_code_point_members;
    }

    @Override
    public int hashCode()
    {
        return this.__code_point_members_as_String.hashCode();
    }

    @Override
    public String toString()
    {
        return Internal_Identity.Text(this);
    }

    @Override
    public boolean equals(final Object obj)
    {
        return (obj instanceof MDV_Text specific_obj)
            && this._same(specific_obj);
    }

    @SuppressWarnings("checkstyle:ModifiedControlVariable")
    public static MDV_Text from(final String code_point_members_as_String)
    {
        if (code_point_members_as_String.isEmpty())
        {
            return MDV_Text.__empty;
        }
        boolean has_any_non_BMP = false;
        int count_of_code_point_members = 0;
        for (int i = 0; i < code_point_members_as_String.length(); i++)
        {
            count_of_code_point_members++;
            if (Character.isSurrogate(code_point_members_as_String.charAt(i)))
            {
                if ((i + 1) < code_point_members_as_String.length()
                    && Character.isSurrogatePair(code_point_members_as_String.charAt(i),
                    code_point_members_as_String.charAt(i + 1)))
                {
                    has_any_non_BMP = true;
                    i++;
                }
                else
                {
                    // The .NET String is malformed;
                    // it has a non-paired Unicode surrogate code point.
                    throw new IllegalArgumentException();
                }
            }
        }
        return new MDV_Text(code_point_members_as_String,
            has_any_non_BMP, count_of_code_point_members);
    }

    public static MDV_Text empty_()
    {
        return MDV_Text.__empty;
    }

    public String code_point_members_as_String()
    {
        return this.__code_point_members_as_String;
    }

    public MDV_Boolean same(final MDV_Any topic_1)
    {
        final MDV_Text topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return (topic_1 instanceof MDV_Text specific_topic_1)
            ? MDV_Boolean.from(topic_0._same(specific_topic_1))
            : MDV_Boolean.false_();
    }

    private boolean _same(final MDV_Text topic_1)
    {
        final MDV_Text topic_0 = this;
        return topic_0.__code_point_members_as_String.equals(
            topic_1.__code_point_members_as_String);
    }

    public MDV_Boolean in_order(final MDV_Orderable<MDV_Text> topic_1)
    {
        final MDV_Text topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic_0._in_order((MDV_Text) topic_1));
    }

    private boolean _in_order(final MDV_Text topic_1)
    {
        final MDV_Text topic_0 = this;
        return topic_0.__code_point_members_as_String
            .compareTo(topic_1.__code_point_members_as_String) <= 0;
    }

    public MDV_Boolean so_empty()
    {
        final MDV_Text topic = this;
        return MDV_Boolean.from(topic.__code_point_members_as_String.isEmpty());
    }

    public MDV_Homogeneous<MDV_Text> empty()
    {
        return MDV_Text.__empty;
    }
}
