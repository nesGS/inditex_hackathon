# Use an official eclipse-temurin as a parent image
FROM eclipse-temurin:21.0.5_11-jdk

# Crea y establece el directorio /app como el directorio de trabajo en el contenedor.
# Todas las acciones posteriores (como copiar archivos o ejecutar comandos) se realizarán desde este directorio.
WORKDIR /app

# Copia el archivo Maven Wrapper (mvnw) desde el directorio local al directorio /app en el contenedor.
COPY mvnw .
# Copia la carpeta .mvn (configuraciones adicionales del wrapper) al contenedor.
COPY .mvn .mvn
# Copia el archivo pom.xml (configuración de Maven) al directorio /app.
COPY pom.xml .

# Descarga dependencias antes de compilar (no es obligatorio, pero hace el proyecto más ágil).
RUN ./mvnw dependency:go-offline

# Copia el directorio src, que contiene el código fuente del proyecto.
COPY src src


# RUN: Ejecuta un comando dentro del contenedor durante el build.
# chmod +x mvnw: Asigna permisos de ejecución al archivo mvnw, permitiendo que se ejecute como un script.
RUN chmod +x mvnw

# clean: Elimina la carpeta target antes de compilar.
# install: Compila, empaqueta y coloca el .jar en el repositorio local de Maven.
RUN ./mvnw clean install -DskipTests
# RUN ./mvnw package -DskipTests --> (ALTERNATIVA) Ejecuta el Maven Wrapper para compilar y empaquetar la aplicación en .jar


# Indica que la aplicación dentro del contenedor escucha en el puerto 3000 (INFORMATIVO).
EXPOSE 3000

# Arranca la aplicación al correr el contenedor.
ENTRYPOINT ["java", "-jar", "target/inditex-app.jar"]

#################################################################################################################
# IMPORTANTE: en el pom se añade <finalName>inditex-app</finalName> para asignar el nombre del .jar al compilar #
#################################################################################################################