package at.michaelkoenig.labor152.rest;

import at.michaelkoenig.labor152.exception.RestException;
import at.michaelkoenig.labor152.model.Mitarbeiter;
import at.michaelkoenig.labor152.model.Taetigkeit;
import at.michaelkoenig.labor152.repository.MitarbeiterRepository;
import at.michaelkoenig.labor152.repository.TaetigkeitRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Michael König
 */
@RestController
public class TaetigkeitResource {
    private final TaetigkeitRepository taetigkeitRepository;
    private final MitarbeiterRepository mitarbeiterRepository;

    public TaetigkeitResource(TaetigkeitRepository taetigkeitRepository, MitarbeiterRepository mitarbeiterRepository) {
        this.taetigkeitRepository = taetigkeitRepository;
        this.mitarbeiterRepository = mitarbeiterRepository;
    }

    @GetMapping("/employees")
    public List<Mitarbeiter> getEmployees() {
        return mitarbeiterRepository.findAll();
    }

    @PostMapping("/employees")
    public ResponseEntity<Mitarbeiter> addMitarbeiter(@Valid @RequestBody Mitarbeiter mitarbeiter) {
        mitarbeiter = mitarbeiterRepository.save(mitarbeiter);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/employee/{id}").
                build(mitarbeiter.getMitId());
        return ResponseEntity.created(uri).body(mitarbeiter);
    }

    @GetMapping("/employees/totalworkingtime/{empid}")
    public Integer getTotalWorkingTimeById(@PathVariable String empid) {
        Optional<Mitarbeiter> mitarbeiter = mitarbeiterRepository.findById(empid);
        if (!mitarbeiter.isPresent())
            throw new RestException("Can't find employee " + empid, HttpStatus.NOT_FOUND);

        return mitarbeiterRepository.getTotalWorkingTimeById(empid);
    }

    @GetMapping("/employees/activities/{empid}/{startdate}/{enddate}")
    public List<Taetigkeit> getActivitiesBetween(@PathVariable String empid, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate) {
        Optional<Mitarbeiter> mitarbeiter = mitarbeiterRepository.findById(empid);
        if (!mitarbeiter.isPresent())
            throw new RestException("Can't find employee " + empid, HttpStatus.NOT_FOUND);
        return taetigkeitRepository.findBetweenDates(startdate, enddate);
    }

    @DeleteMapping("/employees/{empid}")
    public void deleteEmployee(@PathVariable String empid) {
        Optional<Mitarbeiter> mitarbeiter = mitarbeiterRepository.findById(empid);
        if (!mitarbeiter.isPresent())
            throw new RestException("Can't find employee " + empid, HttpStatus.NOT_FOUND);
        taetigkeitRepository.deleteAllByUserId(empid);
        mitarbeiterRepository.deleteById(empid);
    }

    @PostMapping("/activities")
    public ResponseEntity<Taetigkeit> addActivitiy(@Valid @RequestBody Taetigkeit taetigkeit) {
        taetigkeit = taetigkeitRepository.save(taetigkeit);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/employee/{id}").
                build(taetigkeit.getTaetId());
        return ResponseEntity.created(uri).body(taetigkeit);
    }

    @GetMapping("/activities/{date}")
    public List<Taetigkeit> getActivitiesByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return taetigkeitRepository.findByDate(date);
    }
}
