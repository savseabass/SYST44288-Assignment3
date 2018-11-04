#include <pthread.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int avg, min, max;
int size = 0;

void *avgCalc(void *param);
void *minCalc(void *param);
void *maxCalc(void *param);


int main(int argc, char *argv[]){

    int nums[argc -1]; //create array holding argument values to be passed on to thread
    for (int i = 0; i < argc-1; i++){
        nums[i] = atoi(argv[i+1]);
	size++;
    }

    pthread_t tid; //thread identifier
    pthread_attr_t attr; //set of thread attributes
    pthread_attr_init(&attr); //get default attributes for multithreading

    //start all threads
    pthread_create(&tid,NULL,avgCalc,nums);
    pthread_create(&tid,NULL,minCalc,nums);
    pthread_create(&tid,NULL,maxCalc,nums);
    pthread_join(tid,NULL);//join threads
    //print results
    printf("The average value is %d\n", avg);
    printf("The minimum value is %d\n", min);
    printf("The maximum value is %d\n", max);

    
}

void *avgCalc(void *param){
    int *nums = (int*)param;//cast void argument to int array
    int i;
    int sum = 0;
    //standard avg calculation loop
    for(i=0; i<size; i++){
    sum += nums[i];
    }
    avg = sum/size;
    pthread_exit(0);

}

void *minCalc(void *param){
    int i;
    int *nums = (int*)param;//cast void argument to int array
    min = nums[0];
    //loop to calculate the min
    for(i=1; i<size; i++){
        if(min > nums[i])
            min = nums[i];
    }
    pthread_exit(0);

}

void *maxCalc(void *param){

    int i;
    int *nums = (int*)param;//cast void argument to int array
    max = nums[0];
    //loop to calculate the max
    for(i=1; i<size; i++){
        if(max < nums[i])
            max = nums[i];
    }
    pthread_exit(0);
}

