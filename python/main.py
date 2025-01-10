def seq_fib(n):
    fib = [0, 1]

    while fib[-1] < n:
        fib.append(fib[-1] + fib[-2])
    
    if n in fib:
        return f"O número {n} existe na sequência de Fibonacci."
    else:
        return f"O número {n} NÃO existe na sequência de Fibonacci."
    
num = int(input("Informe um número para verificar se está na sequência de Fibonacci: "))
print(seq_fib(num))