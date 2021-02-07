package me.liiot.Level2;

import java.util.LinkedList;
import java.util.Queue;

/*
다리를 지나는 트럭
 */
class Truck {
    int weight;
    int start;
    int end;

    public Truck(int weight, int start, int bridgeLength) {
        this.weight = weight;
        this.start = start;
        this.end = start + bridgeLength;
    }
}

public class TruckPassingTheBridge {

    public static void main(String[] args) {

        int[] truckWeights = {7, 4, 5, 6};
        System.out.println(solution(2, 10, truckWeights));
    }

    public static int solution(int length, int weight, int[] truckWeights) {

        Queue<Truck> passing = new LinkedList<>();
        int currentWeight = truckWeights[0];
        int nextTruck = 1;
        int time = 2;

        passing.add(new Truck(truckWeights[0], 1, length));
        while (!passing.isEmpty() ) {
            if (time == passing.peek().end) {
                currentWeight -= passing.peek().weight;
                passing.poll();
            }

            if (nextTruck < truckWeights.length) {
                if (currentWeight + truckWeights[nextTruck] <= weight) {
                    passing.add(new Truck(truckWeights[nextTruck], time, length));
                    currentWeight += truckWeights[nextTruck];
                    nextTruck++;
                }
            }

            time++;
        }

        return time - 1;
    }
}
