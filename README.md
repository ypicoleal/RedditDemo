# Reddit Demo

## Que esta incluido

- Pagina principal 
- Detalle de post

## Que faltó

- Cache request
- Animaciones y algunos tipos de post

## Demo
![demo2](https://github.com/ypicoleal/RedditDemo/raw/master/demo%20reddit.gif)
![demo1](https://github.com/ypicoleal/RedditDemo/raw/master/Screenshot_1544315864.png)


## Arquitectura utilizada
Se utilizo MVP como arquitectura base con teniendo como modelos las clases ubicada en el paquete `model` como presentador tenemos la clase `PostManager` encargada de consumir el servicio RESTful y las vistas representadas por los Activities `MainActivity` y `PostActivity`

### Respuestas a preguntas
- `MainActivity`: Activity responsable de mostrar los post recientes 
- `PostActivity`: Activity responsable de mostrar el detalle de un post
- `model/PostContainer`: Clase que representa la respuesta enviada por el API con la informacion del request
- `model/PostDataContainer`: Clase que representa la data que viene en la respuesta del API
- `manager/PostManager` : Clase encargada de manejar las peticiones y el tratamiento de los posts
- `manager/RedditService` : Listener requerido Retrofit para manejar las peticiones hacia el servidor
- `adapter/HotPostAdapter` : Adapter que maneja la lista de post en la UI
