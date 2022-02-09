#!/usr/bin/env bash

# This wrapper script is very simple right now and assumes that when it is
# invoked the current working directory is Muldis_Reference_Data_Tools_Java
# checkout base dir; this will be improved.

java -jar ./Muldis_Object_Notation_Processor_Reference/target/muldis-object-notation-processor-reference-0.1.jar "$@"
