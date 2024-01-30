# NAME

Muldis Data Library: Java (MDLib) - Library implementation of Muldis Data Language

# VERSION

The fully-qualified name of what this document describes is
`Muldis_Data_Library_Java https://muldis.com 0.1`.

# DESCRIPTION

MDLib is a work in progress.
It is not yet ready to use for its intended purpose, nor is it yet even a
minimum viable product.
This documentation will be rewritten or updated as the project develops.

## What This Is Intended To Be

MDLib provides an implementation of the Muldis Data Language (MDL) type
system and standard library, expressed in terms of the host programming
language Java.

MDLib has multiple parallel implementations in different host programming
languages, which are designed to prioritize mutual uniformity and
similarity to each other over all other concerns.  This is so that its
users can most easily write the same logic in as close to exactly the same
way as possible across every host language, aiding in code portability as
well as with reasoning about the behavior of any such code.  As such, some
design or code style conventions of MDLib will intentionally differ from
official or defacto counterparts of some hosts, though with others they
would hew much more closely, for example naming styles.

MDLib is expressly designed to be a low-overhead leaf dependency for other
projects.  It has zero external dependencies besides its host programming
language's base language and standard library.  Key reasons for this
include simplifying installation, minimizing the possible attack surface
and vulnerabilities it carries to aid security, and minimizing resource
consumption, whether memory or processor or storage.  MDLib is designed to
be as thin a layer as reasonable over the host while satisfying its other
concerns, giving priority to efficiency in memory usage.

## What This Is Today

*TODO.*

## How To Use It

*TODO.*

# AUTHOR

Darren Duncan - darren@DarrenDuncan.net

# LICENSE AND COPYRIGHT

**Muldis Data Library: Java** (MDLib) is Copyright © 2015-2024, Muldis Data Systems, Inc.

<https://muldis.com>

MDLib is free software;
you can redistribute it and/or modify it under the terms of the Apache
License, Version 2.0 (AL2) as published by the Apache Software Foundation
(<https://www.apache.org>).  You should have received a copy of the
AL2 as part of the MDLib distribution, in the file
[LICENSE/Apache-2.0.txt](../LICENSE/Apache-2.0.txt); if not, see
<https://www.apache.org/licenses/LICENSE-2.0>.

Any versions of MDLib that you modify and distribute must carry prominent
notices stating that you changed the files and the date of any changes, in
addition to preserving this original copyright notice and other credits.
MDLib is distributed in the hope that it will be
useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

While it is by no means required, the copyright holder of MDLib
would appreciate being informed any time you create a modified version of
MDLib that you are willing to distribute, because that is a
practical way of suggesting improvements to the standard version.
