package kz.balthazar.eve.recommender;

import kz.balthazar.eve.model.entity.Event;
import kz.balthazar.eve.model.entity.User;
import kz.balthazar.eve.repository.EventRepo;
import kz.balthazar.eve.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.Map.Entry;

@Service
public class Recommender {

    @Autowired
    UserRepo userRepo;

    @Autowired
    EventRepo eventRepo;

    private final Map<Event, Map<Event, Double>> difference = new HashMap<>();
    private final Map<Event, Map<Event, Integer>> frequency = new HashMap<>();
    private final Map<User, HashMap<Event, Double>> outputData = new HashMap<>();

    protected List<Event> events;

    public Map<User, HashMap<Event, Double>> start() {
        events = eventRepo.findAll();
        Map<User, HashMap<Event, Double>> inputData = initializeData();
//        System.out.println("Initial Data\n");
        buildDifferencesMatrix(inputData);
//        System.out.println("\nSlope One predictions\n");
        return predict(inputData);
    }

    public Map<User, HashMap<Event, Double>> initializeData() {
        Map<User, HashMap<Event, Double>> data = new HashMap<>();
        Set<Event> newRecommendationSet;
        List<User> users = userRepo.findAll();
        for (User user : users) {
            HashMap<Event, Double> newUser = new HashMap<>();
            newRecommendationSet = new HashSet<>();
            for (int j = 0; j < 3; j++) {
                newRecommendationSet.add(events.get((int) (Math.random() * 5)));
            }
            for (Event item : newRecommendationSet) {
                newUser.put(item, Math.random());
            }
            data.put(user, newUser);
        }
        data.put(new User("kek"), new HashMap<>());
        return data;
    }

    private void buildDifferencesMatrix(Map<User, HashMap<Event, Double>> data) {
        for (HashMap<Event, Double> user : data.values()) {
            for (Entry<Event, Double> e : user.entrySet()) {
                if (!difference.containsKey(e.getKey())) {
                    difference.put(e.getKey(), new HashMap<>());
                    frequency.put(e.getKey(), new HashMap<>());
                }
                for (Entry<Event, Double> e2 : user.entrySet()) {
                    int oldCount = 0;
                    if (frequency.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = frequency.get(e.getKey()).get(e2.getKey());
                    }
                    double oldDiff = 0.0;
                    if (difference.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = difference.get(e.getKey()).get(e2.getKey());
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    frequency.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    difference.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }
        for (Event j : difference.keySet()) {
            for (Event i : difference.get(j).keySet()) {
                double oldValue = difference.get(j).get(i);
                int count = frequency.get(j).get(i);
                difference.get(j).put(i, oldValue / count);
            }
        }
//        printData(data);
    }

    /**
     * Based on existing data predict all missing ratings. If prediction is not
     * possible, the value will be equal to -1
     * 
     * @param data
     *            existing user data and their items' ratings
     */
    private Map<User, HashMap<Event, Double>> predict(Map<User, HashMap<Event, Double>> data) {
        HashMap<Event, Double> uPred = new HashMap<>();
        HashMap<Event, Integer> uFreq = new HashMap<>();
        for (Event j : difference.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }
        for (Entry<User, HashMap<Event, Double>> e : data.entrySet()) {
            for (Event j : e.getValue().keySet()) {
                for (Event k : difference.keySet()) {
                    try {
                        double predictedValue = difference.get(k).get(j) + e.getValue().get(j);
                        double finalValue = predictedValue * frequency.get(k).get(j);
                        uPred.put(k, uPred.get(k) + finalValue);
                        uFreq.put(k, uFreq.get(k) + frequency.get(k).get(j));
                    } catch (NullPointerException ignored) {
                    }
                }
            }
            HashMap<Event, Double> clean = new HashMap<>();
            for (Event j : uPred.keySet()) {
                if (uFreq.get(j) > 0) {
                    clean.put(j, uPred.get(j) / uFreq.get(j));
                }
            }
            for (Event j : events) {
                if (e.getValue().containsKey(j)) {
                    clean.put(j, e.getValue().get(j));
                } else if (!clean.containsKey(j)) {
                    clean.put(j, -1.0);
                }
            }
            outputData.put(e.getKey(), clean);
        }
//        printData(outputData);
        return outputData;
    }

    private void printData(Map<User, HashMap<Event, Double>> data) {
        for (User user : data.keySet()) {
            System.out.println(user.getLogin() + ":");
            print(data.get(user));
        }
    }

    private void print(HashMap<Event, Double> hashMap) {
        NumberFormat formatter = new DecimalFormat("#0.000");
        for (Event j : hashMap.keySet()) {
            System.out.println(" " + j.getTitle() + " --> " + formatter.format(hashMap.get(j).doubleValue()));
        }
    }

}
