# Lab 2
# Introduction

Le but de ce TP est d'apprendre à utiliser Yocto, son système de recettes et de meta-layers.

# Installation du projet Yocto

## Installation des paquets nécéssaire à l'utilisation de Yocto

Avant de pouvoir utiliser Yocto, nous avons besoin d'installer les paquets suivants:

> sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint3 xterm python3-subunit mesa-common-dev
> sudo apt install make python3-pip

Installation des paquets nécéssaires à la création de la documentation:

> sudo apt pip3 install sphinx sphinx_rtd_theme pyyaml

Installation des paquets nécéssaire pour l'utilisation des qemu (simulation):

> sudo sudo apt-get build-dep qemu
> sudo apt-get remove oss4-dev
> sudo apt-get install tmux

### Debian

Pour Debian, nous devons installer les paquets supplémentaires ci-dessous:
> sudo pip3 install GitPython pylint==1.9.5

##  Yocto

Commençons par télécharger le repo git du projet Yocto:

> mkdir ~/yocto
> cd yocto
> git clone -b gatesgarth git://git.yoctoproject.org/poky.git
> git clone -b gatesgarth git://git.openembedded.org/meta-openembedded

Maintenant que les sources de Yocto sont installé, nous allons télécharger la meta-layer disponible pour les raspberry pi:

> git clone -b gatesgarth git://git.yoctoproject.org/meta-raspberrypi

## Environnement de Yocto

Maintenant, nous ajouter les variables d'environnement du projet Yocto. Cela nous permettra d'utiliser les outils des projets Yocto et OpenEmbedded.
Comme vu précédement dans le cours, nous allons utiliser le script présent dans le dépôt de poky:

> source poky/oe-init-build-env ~/build-ynov-rpi

Dans notre répertoire build-ynov-rpi, nous allons executer les commandes pour ajouter les meta-layers et créer une meta-layer pour notre TP.

> bitbake-layer add-layer ~/yocto/raspberrypi
> bitbake-layer add-layer ~/yocto/meta-openembedded/meta-oe
> bitbake-layers add-layer /home/yelmernissi/git/yocto/meta-openembedded/meta-networking
> bitbake-layers add-layer /home/yelmernissi/git/yocto/meta-openembedded/meta-python
> bitbake-layers add-layer /home/yelmernissi/git/yocto/meta-openembedded/meta-filesystems
> bitbake-layers add-layer /home/yelmernissi/git/yocto/meta-openembedded/meta-multimedia
> bitbake-layer create-layer ~/yocto/meta-ynov-rpi4 # N'executer pas cette commande, elle présente à titre indicatif, à la place, nous allons ajouter celle présente dans notre répertoire lab2
> bitbake-layer add-layer ~/bachelor-embedded-linux/labs/lab2/meta-ynov-rpi4

Votre fichier ~/build-ynov-rpi/conf/bblayers.conf devrait ressembler à cela:
> #POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
> #changes incompatibly
> POKY_BBLAYERS_CONF_VERSION = "2"
> 
> BBPATH = "${TOPDIR}"
> BBFILES ?= ""
> 
> BBLAYERS ?= " \
>   /home/yelmernissi/git/yocto/poky/meta \
>   /home/yelmernissi/git/yocto/poky/meta-poky \
>   /home/yelmernissi/git/yocto/poky/meta-yocto-bsp \
>   /home/yelmernissi/git/yocto/meta-openembedded/meta-oe \
>   /home/yelmernissi/git/yocto/meta-openembedded/meta-multimedia \
>   /home/yelmernissi/git/yocto/meta-openembedded/meta-python \
>   /home/yelmernissi/git/yocto/meta-openembedded/meta-networking \
>   /home/yelmernissi/git/yocto/meta-ynov-rpi4 \
>   /home/yelmernissi/git/yocto/meta-raspberrypi \
>   "

## local.conf

Modifier la variable MACHINE pour votre système, raspberry pi 3/4, ...

Décommenter les lignes suivantes:

> #DL_DIR ?= "${TOPDIR}/downloads"
> #SSTATE_DIR ?= "${TOPDIR}/sstate-cache"
> #TMPDIR = "${TOPDIR}/tmp"

Afin de rendre le build plus rapide, vous pouvez aussi modifier les variables suivantes:

> #Uncomment and set to allow bitbake to execute multiple tasks at once.
> #For a quadcore, BB_NUMBER_THREADS = "4", PARALLEL_MAKE = "-j 4" would
> #be appropriate.
> BB_NUMBER_THREADS = ""
> #Also, make can be passed flags so it run parallel threads e.g.:
> PARALLEL_MAKE = "-j " 

N'oubliez pas d'adapter les variables avec les valeurs correspondant à votre systèmes!

# Docker

Nous allons maintenant ajouter la layer permettant d'utiliser Docker, le sujet de notre précédent TP.

> git clone -b gatesgarth git://git.yoctoproject.org/meta-virtualization ~/yocto/meta-virtualization

Ajouter votre meta-virualisation de la même façon que nous l'avons fait pour les layer précédente!
 

# meta-ynov-rpi4

Vous ferez le gros du travail dans ce répertoire. Commencer par créer un dossier recipes-ynov-rpi4/images. Dans ce dossier, créez un fichier ynov-rpi4-image.bb.

> pico ynovrpi4-image.bb

> require recipes-core/images/core-image-minimal.bb
> 
> IMAGE_INSTALL += "libstdc++ mtd-utils"
> IMAGE_INSTALL += "openssh openssl openssh-sftp-server"
> 
> DISTRO_FEATURES_append = " virtualization"
> ENABLE_UART = "1"
> 
> IMAGE_INSTALL_append = " docker-ce"

# Hello World!

Ecrivons un simple programme, en C, écrivant sur la console "Hello Ynov Campus! Votre recette Yocto est fonctionnelle! :)"
Créer un fichier hello.c et un fichier hello-world_1.0.bb.

> meta-ynov-rpi4/recipe-ynov/hello-world/files/hello.c
> meta-ynov-rpi4/recipe-ynov/hello-world/hello-world_1.0.bb

Rajoutez votre recette dans votre recette ynov-rpi4-image.bb

# Générer votre première image


Dans votre dossier de build, lancer le build de votre image! Indice: bitbake!

# Uploader votre image

Vous pouvez maintenant uploader votre image sur votre carte SD et lancer votre raspberry pi!

> $ dd if=MonImage.rootfs.rpi-sdimg of=/dev/sdb bs=4M

# Compte rendu

Pour rendre votre travail, commencer par créer une nouvelle branche dans notre git.

> cd ~/bachelor-embedded-linux
> git checkout -b nom_prénom-lab2

Faites vos modifications dans cette nouvelle branches, et envoyer les modifications via git. Attention, vous ne devez pas utiliser la fonction merge!
Il y a plusieurs façon de faire ce TP, si je vois des copier-coller du travail de vos camarades ou d'un projet sur internet -> 0/20!

N'oubliez pas d'incorporer tout votre travail, cela inclut les fichiers de configuration du dossier buuild. Ainsi que le résultat de votre compilation: u-boot, image linux.
Vous trouverez le résultat de votre compilation dans le dossier tmp/deploy/images/raspberrypi4/
L'image à le suffix rootfs.rpi-sdimg

## Ajouter vos fichiers

Pour tout les fichiers modifiés:
> git add fichier
> git commit -m "Descriptions de mes modifications"

Envoyez vos modifications:

> git push nom_prénom-lab2

Bonne chance!
