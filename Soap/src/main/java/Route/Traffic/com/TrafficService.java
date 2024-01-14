package Route.Traffic.com;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class TrafficService {

    public TrafficRepository repo;

    @Autowired
    public TrafficService(TrafficRepository repo) {
        this.repo = repo;
    }
    public  List<Traffic> getAllTraffic()
    {
        return repo.findAll();
    }


}
