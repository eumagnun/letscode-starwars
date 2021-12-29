package br.com.danielamaral.starswarssocialnetwork.business;

import br.com.danielamaral.starswarssocialnetwork.model.ItemInventory;
import br.com.danielamaral.starswarssocialnetwork.model.Rebel;
import br.com.danielamaral.starswarssocialnetwork.model.RebelStatus;
import br.com.danielamaral.starswarssocialnetwork.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportBusiness {

    @Autowired
    private RebelRepository rebelRepository;

    public Map<RebelStatus, Double> generateReportPercentRebelsAndTraitors() {
        List<Rebel> list = rebelRepository.findAll();
        double countRebels = list.size();

        Map<RebelStatus, Long> countRebelsAndTraitors = list.stream()
                .collect(Collectors.groupingBy(Rebel::getStatus,Collectors.counting()));

        return  countRebelsAndTraitors.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().doubleValue()/countRebels*100));
    }

    public Map<String, Double> generateReportAverageResourcesByRebel() {
        List<Rebel> listRebels = rebelRepository.findAll();
        double countRebels = listRebels.size();

        List<ItemInventory> fullListInventoryItems = listRebels.stream()
                .collect(Collectors.flatMapping(r -> r.getInventory().getInventoryItems().stream(),Collectors.toList()));

        Map<String, Long> sumItemsQuantity = fullListInventoryItems.stream()
                            .collect(Collectors.groupingBy(i -> i.getItem().getName(),
                                                                  Collectors.summingLong(ItemInventory::getQuantity)));
        return  sumItemsQuantity.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e-> e.getValue().doubleValue()/countRebels));
    }

    public Map<String, Long> generateReportLostPointsRelatedToTraitors() {
        List<Rebel> listRebels = rebelRepository.findAll();
        List<ItemInventory> fullListTraitorsInventoryItems =  listRebels.stream()
                .filter(r -> r.getStatus().equals(RebelStatus.TRAITOR))
                .collect(Collectors.flatMapping(r -> r.getInventory().getInventoryItems().stream(),Collectors.toList()));

        Long sumLostPoints = fullListTraitorsInventoryItems.stream().mapToLong(i -> i.getItem().getPoints() * i.getQuantity()).sum();

        Map<String, Long> result = new HashMap<>();
        result.put("Lost Points",sumLostPoints);
        return result;
    }
}
