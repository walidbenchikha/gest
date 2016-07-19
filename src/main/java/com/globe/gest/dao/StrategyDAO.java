package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.exception.DuplicateStrategyException;
import com.globe.gest.exception.StrategyNotFoundException;
import com.globe.gest.model.Strategy;

public interface StrategyDAO {

    public void addStrategy(Strategy strategy) throws DuplicateStrategyException;

    public Strategy getStrategy(int id) throws StrategyNotFoundException;

    public void updateStrategy(Strategy strategy) throws StrategyNotFoundException, DuplicateStrategyException;

    public void deleteStrategy(int id) throws StrategyNotFoundException;

    public List<Strategy> getStrategies();

}
