package com.nauroo.ppg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 12/20/2017.
 */

public class CatalogsModel {
    ArrayList<TemperatureModel> temperatures;
    ArrayList<SubstrateModel> substratums;
    ArrayList<DurabilitiesModel> durabilities;
    ArrayList<EnvironmentsModel> environments;

    public ArrayList<TemperatureModel> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(ArrayList<TemperatureModel> temperatures) {
        this.temperatures = temperatures;
    }

    public ArrayList<SubstrateModel> getSubstratums() {
        return substratums;
    }

    public void setSubstratums(ArrayList<SubstrateModel> substratums) {
        this.substratums = substratums;
    }

    public ArrayList<DurabilitiesModel> getDurabilities() {
        return durabilities;
    }

    public void setDurabilities(ArrayList<DurabilitiesModel> durabilities) {
        this.durabilities = durabilities;
    }

    public ArrayList<EnvironmentsModel> getEnvironments() {
        return environments;
    }

    public void setEnvironments(ArrayList<EnvironmentsModel> environments) {
        this.environments = environments;
    }
}
