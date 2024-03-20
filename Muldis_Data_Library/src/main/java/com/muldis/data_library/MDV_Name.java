package com.muldis.data_library;

public final class MDV_Name implements MDV_Orderable<MDV_Name>
{
    private static final MDV_Name __empty = new MDV_Name("");

    // A value of the .NET class String is immutable.
    // It should be safe to pass around without cloning.
    private final String __code_point_members_as_String;

    private MDV_Name(final String code_point_members_as_String)
    {
        this.__code_point_members_as_String = code_point_members_as_String;
    }

    @Override
    public int hashCode()
    {
        return this.__code_point_members_as_String.hashCode();
    }

    @Override
    public String toString()
    {
        return Internal_Identity.Name(this);
    }

    @Override
    public boolean equals(final Object obj)
    {
        return (obj instanceof MDV_Name specific_obj)
            && this._same(specific_obj);
    }

    @SuppressWarnings("checkstyle:ModifiedControlVariable")
    public static MDV_Name from(final String code_point_members_as_String)
    {
        if (code_point_members_as_String.isEmpty())
        {
            return MDV_Name.__empty;
        }
        for (int i = 0; i < code_point_members_as_String.length(); i++)
        {
            if (Character.isSurrogate(code_point_members_as_String.charAt(i)))
            {
                if ((i + 1) < code_point_members_as_String.length()
                    && Character.isSurrogatePair(code_point_members_as_String.charAt(i),
                    code_point_members_as_String.charAt(i + 1)))
                {
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
        return new MDV_Name(code_point_members_as_String);
    }

    public static MDV_Name empty_()
    {
        return MDV_Name.__empty;
    }

    public String code_point_members_as_String()
    {
        return this.__code_point_members_as_String;
    }

    public MDV_Boolean same(final MDV_Any topic_1)
    {
        final MDV_Name topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return (topic_1 instanceof MDV_Name specific_topic_1)
            ? MDV_Boolean.from(topic_0._same(specific_topic_1))
            : MDV_Boolean.false_();
    }

    private boolean _same(final MDV_Name topic_1)
    {
        final MDV_Name topic_0 = this;
        return topic_0.__code_point_members_as_String.equals(
            topic_1.__code_point_members_as_String);
    }

    public MDV_Boolean in_order(final MDV_Orderable<MDV_Name> topic_1)
    {
        final MDV_Name topic_0 = this;
        if (topic_1 == null)
        {
            throw new IllegalArgumentException();
        }
        return MDV_Boolean.from(topic_0._in_order((MDV_Name) topic_1));
    }

    private boolean _in_order(final MDV_Name topic_1)
    {
        final MDV_Name topic_0 = this;
        return topic_0.__code_point_members_as_String
            .compareTo(topic_1.__code_point_members_as_String) <= 0;
    }
}
