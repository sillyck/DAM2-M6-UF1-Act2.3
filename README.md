# DAM2-M6-UF1-Act2.3

1.- Crea un programa Java que inserti un empleat a la taula EMPLEADOS. El programa rep des de la consola els valors a inserir, que són: EMP_NO, APELLIDO, OFICIO, DIR, SALARIO, COMISION, DEPT_NO, FECHA_ALTA.

Abans d'inserir ha de fer les següents comprovacions:

- que el departament existeixi a la taula DEPARTAMENTOS, si no existeix no s'insereix.

- que el número de l'empleat no existeixi, si existeix no s'insereix.

- que el salari sigui >0, en altre cas no s'insereix.

- que el director (DIR, és el número d'empleat del seu director) existeixi a la taula EMPLEADOS, si no existeix no s'insereix.

La data d'alta de l'empleat és l'actual.

Quan s'insereixi la filera, visualitzar el missatge, i si no s'insereix, visualitzar el motiu (departament inexistent, núm. empleat duplicat, ...)

2.- Utilitza la interfície PreparedStatement per visualitzar el APELLIDO, SALARIO i OFICIO dels empleats d'un departament el número del qual és rep per línia de comandes. Visualitza també el nom del departament. Visualitza al final el salari mig  i el número d'empleats del departament.  Si el departament no existeix a la taula DEPARTAMENTOS, visualitza un missatge indicant-ho.

Envia tant el codi, com captures de pantalla mostrant el correcte funcionament d'aquest.
