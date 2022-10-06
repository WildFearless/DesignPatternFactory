## Design Pattern Factory

Cette présentation aura pour but de vous introduire au patron de conception 'factory' aussi appelé fabrique. Tout
d'abord commençons par voir un exemple qui nous aidera à comprendre ce dont il s'agit.

Tout d'abord le design pattern 'factory' est un pattern créationnel qui permet l'instanciation d'objets dérivant d'un
type abstrait. La classe d'objet qui sera envoyée au client 'appelant' n'est donc pas connue par celui-ci. Voyons tout
d'abord un exemple où le patron n'est pas appliquée et l'amélioration du code lorsqu'on décide de l'appliquer.

Imaginons une application qui a besoin de convertir un objet Song en sa représentation en String selon un format spécifique
(par exemple JSON ou XML). 

```python
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

```python
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

```php
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

##

* Les sous-classes peuvent modifier les classes des objets retournés par la méthode fabrique.


## Autres exemples d'utilisation du pattern Factory

* La fonction document.createElement(typeOfElement) du DOM HTML5 fait appel à une factory pour renvoyer les éléments au code appelant
* Les ConnectionFactory pour la connexion à une base de donnée
```java
public class ConnectionFactory {
       private static Connection connection = null;

    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/";
    public static final String USER = "HR";
    public static final String PASS = "HR";

    public static Connection getConnection()  {
       try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
       }
        try {
          
            connection = DriverManager.getConnection(URL, USER, PASS);
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public void closeConnection() throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }
}
```

## Les forces du design pattern Factory

* Nous permet de cacher l'implémentation ou la création des objets à l'utilisateur
* Nous permet de changer notre application plus facilement en raison du couplage faible qu'il favorise
![problem1-fr](https://user-images.githubusercontent.com/58618673/194399540-1eda8b71-51af-4548-8588-7b66537e6ef8.png)
![solution3-fr](https://user-images.githubusercontent.com/58618673/194400995-347c884d-bd8c-43e7-9703-e3239b7470fc.png)
* Il respecte le principe de responsabilité unique et évite les immenses fonctions monolithiques


## Les faiblesses du design pattern Factory

* Peut rendre le code un peu plus difficile à comprendre car on fait appel à un plus haut niveau d'abstraction
* Souvent utilisé de façon incorrecte sans réflexion de comment le programme sera appelé à évoluer
* Peut demander un peu plus de ressources donc ne serait pas judicieux à utiliser dans une application roulant sur un système à ressources limitées tel qu'un embedded system.

![db58cd1c2866ebe4bfd72b4370e5d0f5](https://user-images.githubusercontent.com/58618673/194405890-2b2c421a-1113-42e3-9dfe-1dab97f933cf.png)