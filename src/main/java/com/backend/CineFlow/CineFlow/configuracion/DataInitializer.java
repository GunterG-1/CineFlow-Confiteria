package com.backend.CineFlow.CineFlow.configuracion;

import com.backend.CineFlow.CineFlow.model.Alimento;
import com.backend.CineFlow.CineFlow.model.Combo;
import com.backend.CineFlow.CineFlow.repository.AlimentoRepositorio;
import com.backend.CineFlow.CineFlow.repository.ComboRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final AlimentoRepositorio alimentoRepositorio;
    private final ComboRepositorio comboRepositorio;

    @Override
    public void run(String... args) throws Exception {
        log.info("Inicializando datos de prueba...");

        // Verificar si ya existen datos
        if (alimentoRepositorio.count() > 0) {
            log.info("Datos ya existen en la base de datos. Saltando inicialización.");
            return;
        }

        // Crear alimentos
        List<Alimento> alimentos = new ArrayList<>();
        
        alimentos.add(Alimento.builder()
            .nombre("Palomitas Grandes")
            .descripcion("Palomitas de maíz tostadas frescas")
            .precio(3.99)
            .cantidadDisponible(100)
            .categoria("Bebidas y Snacks")
            .activo(true)
            .rutaImagen("/images/palomitas.jpg")
            .build());

        alimentos.add(Alimento.builder()
            .nombre("Refresco Mediano")
            .descripcion("Refrescos variados helados")
            .precio(2.50)
            .cantidadDisponible(150)
            .categoria("Bebidas y Snacks")
            .activo(true)
            .rutaImagen("/images/refresco.jpg")
            .build());

        alimentos.add(Alimento.builder()
            .nombre("Hot Dog")
            .descripcion("Hot dog clásico con toppings")
            .precio(4.50)
            .cantidadDisponible(80)
            .categoria("Comida Rápida")
            .activo(true)
            .rutaImagen("/images/hotdog.jpg")
            .build());

        alimentos.add(Alimento.builder()
            .nombre("Nachos con Queso")
            .descripcion("Nachos crujientes con queso derretido")
            .precio(5.99)
            .cantidadDisponible(60)
            .categoria("Comida Rápida")
            .activo(true)
            .rutaImagen("/images/nachos.jpg")
            .build());

        alimentos.add(Alimento.builder()
            .nombre("Caramelo de Azúcar")
            .descripcion("Caramelo esponjoso surtido")
            .precio(2.00)
            .cantidadDisponible(200)
            .categoria("Dulces")
            .activo(true)
            .rutaImagen("/images/caramelo.jpg")
            .build());

        alimentos.add(Alimento.builder()
            .nombre("Chocolate Caliente")
            .descripcion("Chocolate caliente cremoso")
            .precio(3.50)
            .cantidadDisponible(90)
            .categoria("Bebidas Calientes")
            .activo(true)
            .rutaImagen("/images/chocolate.jpg")
            .build());

        // Guardar alimentos
        List<Alimento> alimentosGuardados = alimentoRepositorio.saveAll(alimentos);
        log.info("Se han creado {} alimentos", alimentosGuardados.size());

        // Crear combos
        List<Combo> combos = new ArrayList<>();

        // Combo 1: Película Clásica
        Combo combo1 = Combo.builder()
            .nombre("Combo Película Clásica")
            .descripcion("Palomitas grandes + Refresco mediano + Caramelo")
            .precio(8.99)
            .cantidadDisponible(50)
            .activo(true)
            .rutaImagen("/images/combo-clasico.jpg")
            .build();
        combo1.setAlimentos(List.of(alimentosGuardados.get(0), alimentosGuardados.get(1), alimentosGuardados.get(4)));
        combos.add(combo1);

        // Combo 2: Película Deluxe
        Combo combo2 = Combo.builder()
            .nombre("Combo Película Deluxe")
            .descripcion("Hot dog + Nachos con queso + Refresco mediano")
            .precio(14.99)
            .cantidadDisponible(40)
            .activo(true)
            .rutaImagen("/images/combo-deluxe.jpg")
            .build();
        combo2.setAlimentos(List.of(alimentosGuardados.get(2), alimentosGuardados.get(3), alimentosGuardados.get(1)));
        combos.add(combo2);

        // Combo 3: Premium
        Combo combo3 = Combo.builder()
            .nombre("Combo Película Premium")
            .descripcion("Palomitas grandes + Hot dog + Nachos + Refresco + Chocolate")
            .precio(19.99)
            .cantidadDisponible(30)
            .activo(true)
            .rutaImagen("/images/combo-premium.jpg")
            .build();
        combo3.setAlimentos(List.of(alimentosGuardados.get(0), alimentosGuardados.get(2), alimentosGuardados.get(3), 
                                     alimentosGuardados.get(1), alimentosGuardados.get(5)));
        combos.add(combo3);

        // Combo 4: Familia Pequeña
        Combo combo4 = Combo.builder()
            .nombre("Combo Familia Pequeña")
            .descripcion("Palomitas 2x + Refresco 2x + Caramelo 2x")
            .precio(16.99)
            .cantidadDisponible(25)
            .activo(true)
            .rutaImagen("/images/combo-familia.jpg")
            .build();
        combo4.setAlimentos(List.of(alimentosGuardados.get(0), alimentosGuardados.get(1), alimentosGuardados.get(4)));
        combos.add(combo4);

        // Combo 5: Postre
        Combo combo5 = Combo.builder()
            .nombre("Combo Postre")
            .descripcion("Caramelo + Chocolate caliente + Caramelo extra")
            .precio(7.50)
            .cantidadDisponible(35)
            .activo(true)
            .rutaImagen("/images/combo-postre.jpg")
            .build();
        combo5.setAlimentos(List.of(alimentosGuardados.get(4), alimentosGuardados.get(5)));
        combos.add(combo5);

        // Guardar combos
        List<Combo> combosGuardados = comboRepositorio.saveAll(combos);
        log.info("Se han creado {} combos", combosGuardados.size());
        log.info("Inicialización de datos completada exitosamente");
    }
}
