#include <pthread.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int avg, min, max;
int size = 0;
void *avgCalc(void *param);
/*
void *minCalc(void *param);
void *maxCalc(void *param);
*/

int main(int argc, char *argv[]){

    int nums[argc -1];
    for (int i = 0; i < argc-1; i++){
        nums[i] = atoi(argv[i+1]);
	size++;
    }

    pthread_t tid; //thread identifier
    pthread_attr_t attr; //set of thread attributes

    pthread_attr_init(&attr); //get default attributes for multithreading
    pthread_create(&tid,NULL,avgCalc,nums);
    pthread_join(tid,NULL);

    printf("avg = %d\n", avg);
}

void *avgCalc(void *param){
	int *nums = (int*)param;
	int i;
	int sum = 0;
	for(i=0; i<size; i++){
	sum += nums[i];
	}
    	avg = sum/size;
	pthread_exit(0);

}
/*
void *minCalc(void *param){

while(argv[i] != NULL){
	

	}
}

void *maxCalc(void *param){

while(argv[i] != NULL){
	

	}
}*/

