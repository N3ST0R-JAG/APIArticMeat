package com.articmeat.apicompras.services;

import com.articmeat.apicompras.models.DetalleCompra;
import com.articmeat.apicompras.repositories.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    public DetalleCompra registrar(DetalleCompra detalle) {
        return detalleCompraRepository.save(detalle);
    }

    public List<DetalleCompra> obtenerPorCompraID(Integer compraID) {
        return detalleCompraRepository.findByCompraID(compraID);
    }
}