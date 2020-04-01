from DNA import DNA
import math
from random import randint

from operator import attrgetter


class Population:

    def scale_number(self, unscaled, to_min, to_max, from_min, from_max):
        return (to_max-to_min)*(unscaled-from_min)/(from_max-from_min)+to_min

    def naturalSelection(self):
        self.matingPool = []
        f = [i.fitness for i in self.population]
        maxFitness = max(f)
        fitness = 0
        n = 0
        for i in self.population:
            fitness = self.scale_number(i.fitness, 0, maxFitness, 0, 1)
            n = math.floor(fitness * 100)
            for j in range(n):
                self.matingPool.append(i)

    def generate(self):
        nexpop = []
        for i in range(len(self.population)):
            p1 = self.matingPool[randint(0, len(self.matingPool)-1)]
            p2 = self.matingPool[randint(0, len(self.matingPool)-1)]

            c = p1.crossover(p2)
            c.mutate(self.mutationRate, self.target)
            nexpop.append(c)
        self.generations = self.generations + 1
        self.population = nexpop

    def evaluate(self):
        f = [i.fitness for i in self.population]
        wr = max(f)
        obj = max(self.population, key=attrgetter('fitness'))
        self.bphrase = obj.phrase
        if wr == 1:
            self.finished = True

    def getBest(self):
        return self.bphrase

    def isFinished(self):
        return self.finished

    def getGen(self):
        return self.generations

    def __init__(self, num, target, mr):
        self.population = [DNA(len(target), target) for i in range((num))]
        self.target = target
        self.matingPool = []
        self.generations = 0
        self.mutationRate = mr
        self.finished = False
        self.bphrase = ''
