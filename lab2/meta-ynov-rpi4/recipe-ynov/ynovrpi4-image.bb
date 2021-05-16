
require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += "libstdc++ mtd-utils"
IMAGE_INSTALL += "openssh openssl openssh-sftp-server"

DISTRO_FEATURES_append = " virtualization"
ENABLE_UART = "1"

IMAGE_INSTALL_append = "/Embedded_Linux/lab2/meta-ynov-rpi4/recipe-ynov/hello-world/files/hello.c"
