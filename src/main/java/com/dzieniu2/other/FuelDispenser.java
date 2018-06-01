package com.dzieniu2.other;

import com.dzieniu2.entity.Container;
import com.dzieniu2.repository.ContainerRepository;

public class FuelDispenser {

    private ContainerRepository containerRepository;

    private Container container;

    private double tanked,price,cost;

    public FuelDispenser(Container container){
        this.containerRepository = new ContainerRepository();
        this.container = container;
        this.price = container.getFuel().getPrice()*0.01;
        this.tanked = 0.0;
        this.cost = 0.0;
    }

    public void tank(){
        if(container.getFuelLeft()>tanked){
            tanked += 0.01*10;
            cost += price*10;
        }
    }

    public void pay(){
        Container container = containerRepository.findById(this.container.getId());
        container.setFuelLeft(container.getFuelLeft()-tanked);
        containerRepository.update(container);

        tanked = 0;
        cost = 0;
    }

    public Container getContainer() {
        return this.container;
    }

    public double getTanked() {
        return tanked;
    }

    public double getCost() {
        return cost;
    }
}
