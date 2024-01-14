package Route.Traffic.com;

import com.traffic.route.Traffics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.traffic.route.GetAllTrafficResponse;
import com.traffic.route.GetAllTrafficRequest;
import com.traffic.route.Traffic;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class EndPoint {
    private static final String NAMESPACE_URI = "http://Route.Traffic.com";

    @Autowired
    public TrafficService service;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllTrafficRequest")
    @ResponsePayload
    public GetAllTrafficResponse getCountry(@RequestPayload GetAllTrafficRequest request) {
        GetAllTrafficResponse response = new GetAllTrafficResponse();
        List<Route.Traffic.com.Traffic> L=service.getAllTraffic();
    List<Traffic> traffic = L.stream()
            .map(this::convertToTraffic)
            .collect(Collectors.toList());

    Traffics trafficsWrapper = new Traffics();
        trafficsWrapper.getTraffic().addAll(traffic);

        response.setTraffics(trafficsWrapper);
        return response;
}

    private Traffic convertToTraffic(Route.Traffic.com.Traffic Newtraffic) {
        Traffic traffic = new Traffic();
        traffic.setId(Newtraffic.getId());
        traffic.setHour(Newtraffic.getHour());
        traffic.setDate(Newtraffic.getDate());
        traffic.setTrafficPercentage(Newtraffic.getTrafficPercentage());
        traffic.setCityName(Newtraffic.getCityName());
        traffic.setCountryName(Newtraffic.getCountryName());
        traffic.setStreetName(Newtraffic.getStreetName());
        return traffic;
    }
}

