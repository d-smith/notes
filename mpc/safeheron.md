# Safeheron MPC

Safeheron open source [overview](https://www.safeheron.com/en-US/open-source/)

MPC project [here](https://github.com/Safeheron/multi-party-ecdsa-cpp)

Some notes

* As of June 2023 there is a mismatch between some of the work in progress to support the STARK curve and the code on the related branch and tests in the main. To deal with this I will fork the code to make the following mods:

# Dependencies

* [crypto suite](https://github.com/Safeheron/safeheron-crypto-suites-cpp)
    * google test [source](https://github.com/google/googletest)
    * google test [build instructions](https://github.com/google/googletest/tree/main/googletest)
* [openssl](https://github.com/openssl/openssl)
    * Configure build to install in a standalone location to avoid putting OS is an unexpected state
    * [Detailed build and install instructions](https://github.com/openssl/openssl/blob/master/INSTALL.md)
    * mkdir $HOME/openssl
    * ./Configure --prefix=$HOME/openssl --openssldir=$HOME/openssl
    * make
    * make test
    * make install