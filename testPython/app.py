import datetime

x = datetime.datetime(2022, 6, 14)

while(x < datetime.datetime(2023, 1, 1)):
    for i in ["Miercoles", "Jueves", "Viernes", "Sabado", "domingo", "Lunes", "martes"]:
        x = x + datetime.timedelta(days=1)
        print(str(x) + i + " SI DIA")
    for i in ["Miercoles", "Jueves", "Viernes", "Sabado", "domingo"]:
        x = x + datetime.timedelta(days=1)
        print(str(x) +i +  " NO")

    for i in ["Lunes", "martes", "Miercoles", "Jueves"]:
        x = x + datetime.timedelta(days=1)
        print(str(x) + i + " SI NOCHE")
    
    for i in [ "Viernes", "Sabado", "domingo", "lunes", "martes"]:
        x = x + datetime.timedelta(days=1)
        print(str(x) + i + " NO")