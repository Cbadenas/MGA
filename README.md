# MGA Global Mobility Apex test


## PokeDex

La idea de crear una Pokedex es que disponemos de una api publica [AQUI](https://pokeapi.co/)
y a su vez, gracias a [ESTE REPO](https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png)
podemos acceder a los sprites de cada pokemon.

## Implementacion

Configuracion simple de una arquitectura clean, con separacion en capas
1. app
    Contiene un package main, el cual consta de
    - Activity principal
    - Sealed class Navigation, que define las distintas rutas de las pantallas ( hoy solo una )
    - Application class
2. domain
3. data

(se podria optar por una separacion por features, pero no encontre sentido en hacer esa configuracion en este momento)


## Instrucciones

Nada especifico. Clonar el repo, compilar y a correr.







