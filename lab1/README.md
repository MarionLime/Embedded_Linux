# TP #1
# Construction d'un conteneur contenant crosstool-ng
Le but de ce TP est d'apprendre à utiliser Docker.
## Installation de Docker
### Mise à jour du système
> sudo apt update && sudo apt upgrade

### Installation du paquet et du dépot
> sudo apt install apt-transport-https ca-certificates curl gnupg2 software-properties-common
Ajouter le dépot contenant Docker:
#### Debian
> curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

Ajouter le dépot à votre fichier sources (/etc/sources.list)

> sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian focal stable" 
> sudo apt update
> sudo apt install docker-ce

#### Ubuntu et Mint

> sudo apt install apt-transport-https ca-certificates curl gnupg2 software-properties-common

> curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
> sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable" 
> sudo apt update
> sudo apt install docker-ce

### Verifier que votre installation fonctionne

> sudo apt-cache policy docker-ce
> sudo apt install docker-ce
> sudo systemctl status docker

# Build
Pour générer l'image, renommer le fichier Dockerfile_skeleton en Dockerfile.
> mv Dockerfile_skeleton Dockerfile

Et executer la commande build

> docker image build [OPTIONS] PATH | URL | -

Une fois l'image construite, vous pouvez l'exectuer dans docker:
> docker run -it IMAGE

## Créer votre Dockerfile
Le but de ce TP sera de créer votre image Docker (conteneur) et d'installer une cross-toolchain (Crosstool-ng).
Utilisez le skeleton pour construire votre image. Une fois votre image construite, cloner le kernel depuis le dépot git et compiler pour le kernel de votre RPi!
Bonne chance!
