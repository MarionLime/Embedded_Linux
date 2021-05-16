TP #2

Learn to use Yocto, its system and the meta-layer

Going through various stages:
- Installation of the packages required to use Yocto
- Installation of the necessary packages for the creation of the documentation
- Installation of the necessary packages for the use of qemu (simulation)
- Download of the Git repository of the Yocto project
- Download of the meta-layer for Raspberry pi3
- Added project environment variables to use Yocto and OpenEmbedded
- Addition of the layer to use the docker
- Added meta-virtualization
- Creation of a recipes-ynov-rpi4 / images folder and creation of a "ynov-rpi4-image.bb" file
- Creation of a "Hello.C" file and a "hello-world_1.0.bb" file
- Writing the program in "Hello.C": "Hello Ynov Campus! Your Yocto recipe is functional! :)"
- Addition of the recipe in your "ynov-rpi4-image.bb"
- Launch of the image build: $ bitbake core-image-minimal


# Uploader votre image

Vous pouvez maintenant uploader votre image sur votre carte SD et lancer votre raspberry pi!

> $ dd if=MonImage.rootfs.rpi-sdimg of=/dev/sdb bs=4M

N'oubliez pas d'incorporer tout votre travail, cela inclut les fichiers de configuration du dossier buuild. Ainsi que le résultat de votre compilation: u-boot, image linux.
Vous trouverez le résultat de votre compilation dans le dossier tmp/deploy/images/raspberrypi4/
L'image à le suffix rootfs.rpi-sdimg

