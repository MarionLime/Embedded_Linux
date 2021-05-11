# Please update this skeleton 
# Don't forget to write commentaries in English instead of French for you would be asked to in a work environment!
# Based on Ubuntu "" 20.04 => Your version of Ubuntu or else!
FROM ubuntu:latest as ynov-ctng-latest

# LABEL about the custom image
LABEL maintainer="marion.tapia@ynov.com" 
# Your Ynov Bordeaux Campus student email address
LABEL version="0.1" 
# First version, nothing to change!
LABEL description="" 
# Add a relevant description of the image here! (Recommended)

# Make the creation of docker images easier so that CTNG_UID/CTNG_GID have
# a default value if it's not explicitly specified when building. This
# will allow publishing of images on various package repositories (e.g.
# docker hub, gitlab containers). dmgr.sh can still be used to set the
# UID/GID to that of the current user when building a custom container.
ARG CTNG_UID=1000
ARG CTNG_GID=1000
# File to configure for your raspberry pi version
ARG CONFIG_FILE

# Crosstool-ng must be executed from a user that isn't the superuser (root)
# You must create a user and add it to the sudoer group
# Help : https://phoenixnap.com/kb/how-to-create-sudo-user-on-ubuntu
# https://phoenixnap.com/kb/how-to-create-sudo-user-on-ubuntu
RUN adduser newuser \
&& usermod -aG sudo newuser 
# add new userto the sudo group
# You will need to update the repository list before updating your system in order to install some of the packages
# Use the sources.list provided with the lab materials
# On ubuntu, lookup the command add-apt-repository and the repos universe and multiverse?
# RUN <Commands to add repo, if necessary??>

RUN apt-get -y update && apt-get -y upgrade
# Install necessary packages to run crosstool-ng
# You don't remember the previous lectures on the crosstool-ng?
# Use google : install crosstool-ng <Your distribution>??
RUN DEBIAN_FRONTEND="8" apt-get -y install tzdata
# command allowing the installation of Tzdata, the choice of the region blocked the progress of the build of the docker."8" = Europe.
RUN  apt-get install -y gcc-arm-linux-gnueabi \
&& apt-get install -y build-essential git autoconf bison flex help2man gawk libtool-bin libncurses5-dev gperf texinfo unzip

# Install Dumb-init
# https://github.com/Yelp/dumb-init
RUN apt-get install -y dumb-init
RUN echo 'export PATH=/opt/ctng/bin:$PATH' >> /etc/profile
#ENTRYPOINT [ "/usr/local/bin/dumb-init", "--" ]

# Login with user 
USER newuser
WORKDIR /home/newuser
# Download and install the latest version of crosstool-ng
# https://github.com/crosstool-ng/crosstool-ng.git
RUN git clone -b master --single-branch --depth 1 \
    https://github.com/crosstool-ng/crosstool-ng.git ct-ng
WORKDIR /home/newuser/ct-ng
RUN ./bootstrap \
&& ./configure --enable-local \
&& make
ENV PATH=/home/newuser/.local/bin:$PATH
COPY ${CONFIG_FILE} config
# Build ct-ng
RUN ./configure --${CONFIG_FILE} \
&& make

ENV TOOLCHAIN_PATH=/home/dev/x-tools/${CONFIG_FILE}
ENV PATH=${TOOLCHAIN_PATH}/bin:$PATH

CMD ["bash"]
