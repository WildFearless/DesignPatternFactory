## Design Pattern Factory

Cette présentation aura pour but de vous introduire au patron de conception 'factory' aussi appelé fabrique. Tout
d'abord commençons par voir un exemple qui nous aidera à comprendre ce dont il s'agit.

Tout d'abord le design pattern 'factory' est un pattern créationnel qui permet l'instanciation d'objets dérivant d'un
type abstrait. La classe d'objet qui sera envoyée au client 'appelant' n'est donc pas connue par celui-ci. Voyons tout
d'abord un exemple où le patron n'est pas appliquée et l'amélioration du code lorsqu'on décide de l'appliquer.

Imaginons une application qui a besoin de convertir un objet Song en sa représentation en String selon un format spécifique
(par exemple JSON ou XML). 

```
# In serializer_demo.py

import json
import xml.etree.ElementTree as et

class Song:
    def __init__(self, song_id, title, artist):
        self.song_id = song_id
        self.title = title
        self.artist = artist


class SongSerializer:
    def serialize(self, song, format):
        if format == 'JSON':
            song_info = {
                'id': song.song_id,
                'title': song.title,
                'artist': song.artist
            }
            return json.dumps(song_info)
        elif format == 'XML':
            song_info = et.Element('song', attrib={'id': song.song_id})
            title = et.SubElement(song_info, 'title')
            title.text = song.title
            artist = et.SubElement(song_info, 'artist')
            artist.text = song.artist
            return et.tostring(song_info, encoding='unicode')
        else:
            raise ValueError(format)
```

Dans cette exemple utilisant le code Python nous avons une classe d'objet simple représentant une Song et nous avons
ensuite un SongSerializer qui contient la méthode `serialize(self, song, format)`. Les différentes implémentations nécessaires
afin d'obtenir la sérialisation des objets se trouvent dans la même méthode et est dispatchée par un if else.

On peut trouver que ce code est simple mais il ne respecte pas plusieurs principes tels que principe de responsabilité unique
qui requiert qu'une classe ou une méthode ne fasse qu'une seule chose. En plus de favoriser la lisibilité du code le respect 
de ce principe est spécialement important puisque ce code sera difficilement extensible et difficile à maintenir. Supposons que nous
devons ajouter des nouveaux formats autres que XML ou JSON, cela alourdira la méthode, si nous modifions l'objet Song nous devrons également
modifier chacun des implémentations donc il serait plus judicieux d'extraire les implémentations de la méthode `serialize(self, song, format)`.

```
class SongSerializer:
    def serialize(self, song, format):
        serializer = get_serializer(format)
        return serializer(song)


def get_serializer(format):
    if format == 'JSON':
        return _serialize_to_json
    elif format == 'XML':
        return _serialize_to_xml
    else:
        raise ValueError(format)


def _serialize_to_json(song):
    payload = {
        'id': song.song_id,
        'title': song.title,
        'artist': song.artist
    }
    return json.dumps(payload)


def _serialize_to_xml(song):
    song_element = et.Element('song', attrib={'id': song.song_id})
    title = et.SubElement(song_element, 'title')
    title.text = song.title
    artist = et.SubElement(song_element, 'artist')
    artist.text = song.artist
    return et.tostring(song_element, encoding='unicode')
```

Dans cet exemple, nous avons grandement simplifié la méthode `serialize` et nous appelons `get_serializer` afin d'obtenir
la bonne fonction. Pour bien comprendre il est nécessaire de savoir que les fonctions en python sont des objets comme 
en JavaScript. On peut voir ici que le code est simplifié et que le principe de responsabilité unique est respecté.

L'idée principale ici est que le code appelant demande un objet serializer en spécifiant un paramètre mais ce dernier
n'a pas à le construire lui même ou devoir spécifier d'autres paramètres que ce qu'il veut, et il le reçoit.

Voici un autre exemple en PHP qui illustre le principe plus simplement et qui ressemble à notre implémentation en java dans ce repository :

```
/* Factory and car interfaces */

interface CarFactory
{
    public function makeCar(): Car;
}

interface Car
{
    public function getType(): string;
}

/* Concrete implementations of the factory and car */

class SedanFactory implements CarFactory
{
    public function makeCar(): Car
    {
        return new Sedan();
    }
}

class Sedan implements Car
{
    public function getType(): string
    {
        return 'Sedan';
    }
}

/* Client */

$factory = new SedanFactory();
$car = $factory->makeCar();
print $car->getType();
```


## Exemples d'utilisation du pattern dans des 

* La fonction document.createElement(typeOfElement) du DOM HTML5 fait appel à une factory pour renvoyer les éléments au code appelant
* Les ConnectionFactory pour la connexion à une base de donnée

## Les forces du design pattern Factory

* Nous permet de cacher l'implémentation ou la création des objets à l'utilisateur
* Nous permet de changer notre application plus facilement en raison du couplage faible qu'il favorise
* Il respecte le principe de responsabilité unique et évite les immenses fonctions monolithiques


## Les faiblesses

* Peut rendre le code un peu plus difficile à comprendre car on fait appel à un plus haut niveau d'abstraction
* Souvent utilisé de façon incorrecte sans réflexion de comment le programme sera appelé à évoluer
* Peut demander un peu plus de ressources donc ne serait pas judicieux à utiliser dans une application roulant sur un système à ressources limitées tel qu'un embedded system.