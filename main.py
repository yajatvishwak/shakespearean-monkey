from Population import Population
target = "yajatvishwakarma"
popmax = 10000
mutationRate = 2
p = Population(popmax, target, mutationRate)
while not(p.isFinished()):
    p.naturalSelection()
    p.generate()
    p.evaluate()
    print(p.getBest())

print("Generation: " + str(p.getGen()))
print("Evolved Phrase: " + str(p.getBest()))
print("Model Converged -- Evolved successfully")
