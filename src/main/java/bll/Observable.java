package bll;

import interfaces.Observer;

public class Observable {
    public void registerObserver(Observer observer) {
        if (observer != null) {
            DeliveryService.getObservers().add(observer);
        }
    }

    public void notifyObservers(DeliveryService deliveryService) {
        for ( Observer observer : DeliveryService.getObservers()) {
            observer.update(deliveryService);
        }
    }

    public void removeObserver(Observer observer) {
        if (observer != null)
            DeliveryService.getObservers().remove(observer);
    }
}
