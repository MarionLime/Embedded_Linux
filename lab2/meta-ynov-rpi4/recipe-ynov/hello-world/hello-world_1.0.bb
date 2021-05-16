DESCRIPTION = "Hello Ynov programme"
LICENSE = "COPYING.MIT" # "Choose wisely" Monty Python, vous connaissez la référence ou non!
LIC_FILES_CHKSUM = "/home/user/Embedded_Linux/lab2/meta-ynov-rpi4/COPYING.MIT" # Faites vos recherches et remplissez cette variable

# Rajouter le lien vers votre fichier
SRC_URI = "meta-ynov-rpi4/recipe-ynov/hello-world/files/hello.c"

S = "${WORKDIR}"

do_compile() {
	# Cadeaux, je vous donne la commande pour compiler
	${CC} hello.c ${LDFLAGS} -o hello
}

# installe le programme dans /usr/bin 
do_install() {
	install -d ${D}${bindir}
        install -m 0755 hello ${D}${bindir}
}
