;;; Se preguntó a ChatGPT lo siguiente: podrías crear un programa en Lisp de conversión de grados Farenheit a centígrados

(defun fahrenheit-to-celsius (fahrenheit)
    "Convierte grados Fahrenheit a grados Celsius"
    (/ (* (- fahrenheit 32) 5) 9)
)

(defun convertir-fahrenheit-a-celsius ()
    "Solicita al usuario un valor en grados Fahrenheit y muestra el equivalente en grados Celsius."
    (format t "Ingrese la temperatura en grados Fahrenheit: ") ;;Pide al usuario ingresar la temperatura en Farenheit
    (let ((fahrenheit (read))) ;lee lo ingresado y lo reconoce como fahrenhei
    (format t "La temperatura en grados Celsius es: ~a~%" (fahrenheit-to-celsius fahrenheit))) ;Aquí se hace la conversión
)

(convertir-fahrenheit-a-celsius)
