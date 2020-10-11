#! /bin/bash

#ayy I did it

#make run ARGS="encode ciphertext keytext"
#make run ARGS="decode plaintext keytext"


Help:
$(info ARGS is encode or decode, the text, and then the key)


run:
	javac Main.java
	java Main $(ARGS)

