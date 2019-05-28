conjuros = open("Conjures.txt", "r")
linea = conjuros.readline()
count = 0
largo = 0
while linea != "":
    count += 1
    largo += len(linea)
    linea = conjuros.readline()
    
print("Count: " + str(count))
print("AVG len: " + str(largo/ count))