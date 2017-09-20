package controllers;

import api.ReceiptResponse;
import dao.ReceiptDao;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags/{tag}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;
    final ReceiptDao receipts;

    public TagController(TagDao tags, ReceiptDao receipts) {
        this.tags = tags;
        this.receipts = receipts;
    }

    @GET
    public List<ReceiptResponse> getReceiptsForTag(@PathParam("tag") String tagName) {
        List<Integer> tagIDs = tags.getAllReceiptsIDs(tagName);
        List<ReceiptsRecord> receiptsRecords = receipts.getAllReceipts();

        return receiptsRecords
                .stream()
                .filter(receiptsRecord -> tagIDs.contains(receiptsRecord.getId()))
                .map(ReceiptResponse::new).collect(toList());
    }


    @PUT
    public void toggleTag(@PathParam("tag") String tagName, @NotNull int id) {
          tags.insert(tagName, id);
    }
}
