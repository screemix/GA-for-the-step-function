class gen{
    int[] x;
    int fit;
    public gen(){
        x = new int[14];
    }
    public int fromBinToInt1(){
        int sum = 0;
        for (int i = 0; i <7; i++){
            sum+=x[i]*Math.pow(2,i);
        }
        return sum;
    }
    public int fromBinToInt2(){
        int sum = 0;
        for (int i = 7; i <14; i++){
            sum+=x[i]*Math.pow(2,i-7);
        }
        return sum;
    }
    public void fitness(){
        int sum = 0;
        sum+=Math.floor(fromBinToInt1()+0.5)*Math.floor(fromBinToInt1()+0.5);
        sum+=Math.floor(fromBinToInt2()+0.5)*Math.floor(fromBinToInt2()+0.5);
        fit = sum;
    }
}

public class Main {
    static gen[] generation = new gen[50];
    static int[] fromIntToBin(int a, int b){
        int[] x = new int[14];
        for (int i = 0; i<7; i++){
            x[i] = a%2;
            a=a/2;
        }
        for (int i = 7; i<14; i++){
            x[i] = b%2;
            b = b/2;
        }
        return x;
    }
    static gen mutate(gen target){
        for (int i = 0; i<14; i++){
            target.x[i] = target.x[i]*(int)(Math.random());
        }
        return target;
    }
    static gen offspring(gen parent1, gen parent2){
        gen new_gen = new gen();
        for(int i = 0; i<7;i++){
            new_gen.x[i] = parent1.x[i];
        }
        for(int i = 7; i<14; i++){
            new_gen.x[i] = parent2.x[i];
        }
        return mutate(new_gen);
    }
    static void sort(){
        for (int i = 0; i<50; i++){
            for (int j = 1; j<50; j++){
                if (generation[j-1].fit>generation[j].fit){
                    gen temp = generation[j-1];
                    generation[j-1] = generation[j];
                    generation[j] = temp;
                }
            }
        }
    }
    static void fit_count(){
        for (int i = 0; i<50; i++){
            generation[i].fitness();
        }
    }
    static void first_generation(){
        for (int i = 0; i<50; i++){
            gen new_one = new gen();
            int a = (int)Math.random()*100;
            int b = (int)Math.random()*100;
            new_one.x = fromIntToBin(a,b);
            generation[i] = new_one;
        }
    }
    public static void main(String[] args) {
        first_generation();
        for (int i =0; i<50; i++){
            fit_count();
            sort();
            for (int j = 10; j<50; j++){
                int a = (int)Math.random()*10;
                int b = a = (int)Math.random()*10;
                generation[i]=offspring(generation[a],generation[b]);
            }
        }
        sort();
        System.out.println(generation[0].fit);
    }
}
