# Gestión de productos en una tienda con Java

Actividad formativa 2 — Desarrollo de una Aplicación Simple en Línea.

## Propósito

Aplicación de consola que representa productos mediante una clase, crea dos objetos y muestra su información antes y después de actualizar su stock. La publicación en línea corresponde al repositorio y a la exposición; la aplicación se ejecuta localmente en Java.

## Archivos

- `Producto.java`: código completo con atributos, constructor, métodos y `main`.
- `evidencias/salida_ejecucion.txt`: salida real de la ejecución principal.
- `evidencias/pruebas_validacion.txt`: resultados de 11 pruebas funcionales y de límites.
- `evidencias/depuracion_jdb.txt`: revisión real de los cambios de stock con el depurador Java.
- `.gitignore`: exclusión de archivos compilados y ajustes locales del IDE.

## Requisitos y ejecución

Se necesita un JDK. El proyecto se verificó con OpenJDK 17 y no requiere bibliotecas externas.

Desde la carpeta que contiene `Producto.java`:

```bash
javac -encoding UTF-8 Producto.java
java Producto
```

Los productos iniciales son Cuaderno ($35.50, 20 unidades) y Bolígrafo ($12.00, 50 unidades). Después de llamar a `actualizarStock`, sus existencias quedan en 15 y 60. El separador decimal de la impresión puede variar según la configuración regional del equipo.

## Conceptos aplicados

La clase `Producto` define los atributos privados `nombre`, `precio` y `stock`. Su constructor recibe esos datos y rechaza valores negativos de precio o stock. `mostrarInformacion()` imprime el estado de cada objeto. `actualizarStock(int nuevoStock)` reemplaza la cantidad disponible por un valor no negativo; no suma unidades al stock anterior. `main` crea dos instancias con `new` y ejecuta los métodos sobre cada una.

## Uso en IntelliJ IDEA

1. Crear un proyecto Java y seleccionar un JDK 17 o posterior.
2. Crear la clase `Producto` dentro de la carpeta de fuentes, sin declaración de paquete, y copiar el contenido de `Producto.java`.
3. Ejecutar `main` desde la opción Run situada junto al método.
4. Para depurar, colocar un punto de interrupción en `this.stock = nuevoStock;` y ejecutar Debug. Revisar `this.stock` y `nuevoStock` antes de avanzar una línea. En la primera llamada deben verse 20 y 15; después de la asignación, 15. En la segunda llamada, el cambio es de 50 a 60.

La evidencia incluida corresponde a ejecución por consola y JDB. El recorrido de IntelliJ describe cómo reproducir esa revisión en el IDE.

## Publicación en GitHub

1. Crear una cuenta o iniciar sesión en GitHub. Crear un repositorio vacío llamado, por ejemplo, `gestion-productos-java`. No inicializarlo con README, licencia ni `.gitignore`, porque estos archivos ya están preparados localmente.
2. Abrir una terminal en esta carpeta. Si Git solicita nombre y correo, configurar los datos reales de la cuenta.
3. Ejecutar los siguientes comandos. Sustituir `URL_REAL_DEL_REPOSITORIO` por la dirección HTTPS copiada del repositorio creado:

```bash
git init -b main
git add .
git commit -m "Implementa gestion de productos en Java"
git remote add origin URL_REAL_DEL_REPOSITORIO
git push -u origin main
```

Autenticarse cuando Git lo solicite. Después, abrir el repositorio, comprobar los archivos y copiar su enlace en el informe. Estos comandos son una guía de publicación; los archivos no acreditan un commit ni un push remotos ya realizados.

## Exposición y grabación

Usar la presentación entregada y sus notas del presentador. Mostrar el código y ejecutar `main` durante la exposición. Grabar la sesión, subirla al servicio permitido por la institución y pegar el enlace real de visualización en el informe. Verificar que el docente pueda acceder tanto al repositorio como al video.

## Fuentes oficiales

- Oracle. (2024, 5 de enero). *Creating classes*. Dev.java. https://dev.java/learn/language/oop/classes-objects/creating-classes/
- Oracle. (2024, 5 de enero). *Defining methods*. Dev.java. https://dev.java/learn/language/oop/classes-objects/defining-methods/
- Oracle. (2024, 5 de enero). *Providing constructors for your classes*. Dev.java. https://dev.java/learn/language/oop/classes-objects/defining-constructors/
- GitHub. (s. f.). *Agregar código hospedado localmente a GitHub*. Documentación de GitHub. https://docs.github.com/es/migrations/importing-source-code/using-the-command-line-to-import-source-code/adding-locally-hosted-code-to-github
- JetBrains. (2026, 10 de febrero). *Tutorial: Debug your first Java application*. IntelliJ IDEA Documentation. https://www.jetbrains.com/help/idea/debugging-your-first-java-application.html
