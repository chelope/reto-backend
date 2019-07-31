package com.promart.client.service.impl;

import com.promart.client.model.Client;
import com.promart.client.model.ClientWrapper;
import com.promart.client.model.Kpi;
import com.promart.client.repository.ClientRepository;
import com.promart.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Client Service Implementation Class.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
@Service
public class ClientServiceImpl implements ClientService {

    public static final String STANDARD_DESVIATION = "standardDesviation";
    public static final String AVERAGE = "average";

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findById(String s) {
        return null;
    }

    @Override
    public Client save(Client entity) {
        return clientRepository.push(entity);
    }

    @Override
    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<ClientWrapper> listAllWrapper() {
        List<Client> listClients = clientRepository.findAll();
        Map<String, Kpi> kpis = computeKpiClients(listClients);

        List<ClientWrapper> clientWrapperList = listClients.stream()
                .collect(
                        Collectors.mapping(
                                c -> new ClientWrapper(c, c.computeAge(), c.computeProbableDeathDate(kpis.get("standardDeviation").getValue())),
                                Collectors.toList()));

        return clientWrapperList;
    }

    /**
     * @return Map that contains KPIs Average Age and Standard Desviation Age
     */

    @Override
    public List<Kpi> getKpiClients() {
        Map<String, Kpi> mapKpis = computeKpiClients(clientRepository.findAll());
        ArrayList<Kpi> listKpis = new ArrayList<Kpi>(mapKpis.values());
        return listKpis;
    }

    /**
     * @return Map that contains KPIs Average Age and Standard Desviation Age and Clients
     */
    public static Map<String, Kpi> computeKpiClients(List<Client> listClients) {
        Map<String,Kpi> kpis = new HashMap<String, Kpi>();

        List<Integer> ageList = listClients.stream().map(Client::computeAge).collect(Collectors.toList());
        Double standardDeviation = computeStandardDeviation(ageList.toArray(new Integer[0]));
        Double average = computeAverage(ageList.toArray(new Integer[0]));
        Kpi kpiSd = new Kpi(STANDARD_DESVIATION,standardDeviation);
        Kpi kpiAverage = new Kpi(AVERAGE,average);

        kpis.put(STANDARD_DESVIATION, kpiSd);
        kpis.put(AVERAGE, kpiAverage);

        return kpis;
    }

    /**
     * @param collection
     * @return StandardDeviation
     */
    public static double computeStandardDeviation(Number... collection) {
        return Arrays.stream(collection)
                .map(Number::doubleValue)
                .collect(DoubleStatistics.collector())
                .getStandardDeviation();
    }

    /**
     * @param collection
     * @return Average
     */
    public static double computeAverage(Number... collection) {
        return Arrays.stream(collection)
                .map(Number::doubleValue)
                .collect(DoubleStatistics.collector())
                .getAverage();
    }

    /**
     * Double Statistics Class.
     */
    static class DoubleStatistics extends DoubleSummaryStatistics {

        private double sumOfSquare = 0.0d;
        private double sumOfSquareCompensation;
        private double simpleSumOfSquare;


        /**
         * @param value
         */
        @Override
        public void accept(double value) {
            super.accept(value);
            double squareValue = value * value;
            simpleSumOfSquare += squareValue;
            sumOfSquareWithCompensation(squareValue);
        }

        /**
         * @param other
         * @return
         */
        public DoubleStatistics combine(DoubleStatistics other) {
            super.combine(other);
            simpleSumOfSquare += other.simpleSumOfSquare;
            sumOfSquareWithCompensation(other.sumOfSquare);
            sumOfSquareWithCompensation(other.sumOfSquareCompensation);
            return this;
        }

        /**
         * @param value
         */
        private void sumOfSquareWithCompensation(double value) {
            double tmp = value - sumOfSquareCompensation;
            double velvel = sumOfSquare + tmp;
            sumOfSquareCompensation = (velvel - sumOfSquare) - tmp;
            sumOfSquare = velvel;
        }

        /**
         * @return
         */
        public double getSumOfSquare() {
            double tmp = sumOfSquare + sumOfSquareCompensation;
            if (Double.isNaN(tmp) && Double.isInfinite(simpleSumOfSquare)) {
                return simpleSumOfSquare;
            }
            return tmp;
        }

        /**
         * @return StandardDeviation
         */
        public final double getStandardDeviation() {
            long count = getCount();
            double sumOfSquare = getSumOfSquare();
            double average = getAverage();
            return count > 0 ? Math.sqrt((sumOfSquare - count * Math.pow(average, 2)) / (count - 1)) : 0.0d;
        }

        /**
         * @return
         */
        public static Collector<Double, ?, DoubleStatistics> collector() {
            return Collector.of(DoubleStatistics::new, DoubleStatistics::accept, DoubleStatistics::combine);
        }
    }
}
