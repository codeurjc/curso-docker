# Selecciona la imagen base
FROM alpine:3.5

# Instala python y pip
RUN apk add --update py-pip
RUN pip install --upgrade pip

# Copia el fichero de las librerías
COPY requirements.txt /app/

# Instala las librerías python que necesita la app
RUN pip install --no-cache-dir -r /app/requirements.txt

# Copia los ficheros de la aplicación
COPY app.py /app/
COPY templates/index.html /app/templates/

# Indica el puerto que expone el contenedor
EXPOSE 5000

# Comando que se ejecuta cuando se arranque el contenedor
CMD ["python", "/app/app.py"]
