package com.globe.gest.service;

import java.util.List;

import com.globe.gest.exception.DuplicateStrategyException;
import com.globe.gest.exception.StrategyNotFoundException;
import com.globe.gest.model.Strategy;

public interface StrategyService {

    public void addStrategy(Strategy strategy) throws DuplicateStrategyException;

    public Strategy getStrategy(int id) throws StrategyNotFoundException;

    public void updateStrategy(Strategy strategy) throws StrategyNotFoundException, DuplicateStrategyException;

    public void deleteStrategy(int id) throws StrategyNotFoundException;

    public List<Strategy> getStrategies();

}
