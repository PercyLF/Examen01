package com.luna.Examen01.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.luna.Examen01.dto.EventoDto;
import com.luna.Examen01.service.PdfService;
import com.luna.Examen01.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import com.luna.Examen01.entity.Evento;
import com.luna.Examen01.service.EventoService;
import com.luna.Examen01.converter.EventoConverter;
import org.thymeleaf.context.Context;


@RestController
@RequestMapping("/v1/eventos")
public class EventoController {
    @Autowired
    private EventoService service;

    @Autowired
    private EventoConverter converter;
    @Autowired
    private PdfService pdfService;

    @GetMapping
    public ResponseEntity<List<EventoDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<EventoDto> eventos = converter.fromEntity(service.findAll());
//        return ResponseEntity.ok(categorias);
        return new WrapperResponse(true, "success", eventos).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventoDto> create (@RequestBody EventoDto evento) {
        Evento entity = converter.fromDTO(evento);
        EventoDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDto> update (@PathVariable("id") int id, @RequestBody EventoDto evento) {
        Evento entity = converter.fromDTO(evento);
        EventoDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id) {
        service.delete(id);
//        return ResponseEntity.ok(null);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDto> findById (@PathVariable("id") int id) {
        EventoDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        List<EventoDto> eventos = converter.fromEntity(service.findAll());

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = fecha.format(formato);

        //Crear Contexto de Thymeleaf con los datos
        Context context = new Context();
        context.setVariable("registros", eventos);
        context.setVariable("fecha", fechaHora);

        //Llamar Servicio pdf
        byte[] pdfBytes = pdfService.createPdf("eventoReporte", context);

        //configurar encabezados de respuesta http para devolver pdf
        //import or.sprin....http.HttpHeaders;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "reporte_eventos.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
