# Kotlin Base91 (BasE91)
I have been looking for an implementation for a long time. In the end, I made my own! It took me 3 hours but I'm happy.

## Basic idea
Among the 94 printable ASCII characters (0x21-0x7E), basE91 excludes the following three characters

Base91, stylized as basE91, is a form of binary-to-text encoding scheme developed by Joachim Henke[1]. It divides the binary data stream into 13-bit packets which are then encoded in 2 ASCII characters[2]. Assuming eight bits per ASCII character, on average the encoded size will be ³⁄₁₆ larger than the original. That is more efficient than the ¹⁄₃ increase of base64 (which uses four characters to represent three bytes of data) and ¹⁄₄ increase of base85 (which uses five characters to represent four bytes of data).
