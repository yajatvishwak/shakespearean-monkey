import random
import string
import math
from random import randint


class DNA:

    def generateRandomPhrase(self, n):
        letters = string.ascii_lowercase
        letters = letters + ' '
        return ''.join(random.choice(letters) for i in range(n))

    def generateRandomChar(self):
        letters = string.ascii_lowercase
        return random.choice(letters)

    def calFitness(self, target):
        score = 0
        for a, b in zip(self.phrase, target):
            if a == b:
                score = score + 1
        return (score / len(target))

    def crossover(self, partner):

        child = DNA(len(partner.phrase),
                    self.generateRandomPhrase(len(partner.phrase)))
        midpoint = math.floor(randint(0, len(partner.phrase)-1))
        child.phrase = self.phrase[:midpoint] + partner.phrase[midpoint:]
        return child

    def mutate(self, mr, target):
        t = ''
        for i in self.phrase:
            if random.random() > mr:
                t = t + self.generateRandomChar()
            else:
                t = t + i
        self.phrase = t
        self.fitness = self.calFitness(target)

    def __init__(self, n, target):
        self.phrase = self.generateRandomPhrase(n)
        self.fitness = self.calFitness(target)
