package lambda;

@FunctionalInterface
interface Calculate {
    int operation(int a, int b);
}