package se.kth.journalsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.journalsystem.model.Location;
import se.kth.journalsystem.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    public List<Location> getLocationsByOrganizationId(Long organizationId) {
        return locationRepository.findByOrganizationId(organizationId);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location locationDetails) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            Location existingLocation = location.get();
            existingLocation.setAddress(locationDetails.getAddress());
            existingLocation.setDepartment(locationDetails.getDepartment());
            existingLocation.setOrganization(locationDetails.getOrganization());
            return locationRepository.save(existingLocation);
        }
        return null;
    }

    public boolean deleteLocation(Long id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
