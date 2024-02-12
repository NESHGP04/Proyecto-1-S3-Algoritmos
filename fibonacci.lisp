;;;Se le preguntó a ChatGPT: Podrías hacer un programa en lisp para la producción del término n en la serie fibonacci y factorial

(defun fibonacci (n)
    "Calcula el término n de la serie Fibonacci"
    (if (or (= n 0) (= n 1)) ;si n es igual a 0 o 1:
        n ;define n
        (+ (fibonacci (- n 1)) (fibonacci (- n 2))) ;se suma n-1 con n-2
    )
)

(defun factorial (n)
    "Calcula el factorial de n"
    (if (<= n 1) ;si n es menor o igual a 1:
        1 ;define 1
        (* n (factorial (- n 1))) ;multiplicar n por n-1
    )
)

(defun main ()
    "Función principal"
    (format t "Ingrese el valor de n para calcular el término n de la serie Fibonacci: ")
    (let ((n (read)))
    (format t "El término ~a de la serie Fibonacci es: ~a~%" n (fibonacci n)))
    (format t "Ingrese un número para calcular su factorial: ")

    (let ((n (read)))
    (format t "El factorial de ~a es: ~a~%" n (factorial n))
    )
)

(main)
