package cz.housebrains.model.entities.details;

import cz.housebrains.model.entities.Device;

public class Documentation {

    /**
     * Downloads documentation for geven device from manufacturer's website.
     * @param device device which documentation we want to download and view
     */
    public Documentation(Device device){
        System.out.println("Searching documentation for device: " + device.getName());
        System.out.println("Documentation found!");
        System.out.println("Downloading manual from the website. Please wait...");
    }
}
