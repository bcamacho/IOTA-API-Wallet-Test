package com.brandycamacho.iota_lookup;

import java.util.List;

/**
 * Created by brandycamacho on 1/29/18.
 */

public class Transaction {

  String name;
  List<HashItem> items;

  public Transaction(String name, List<HashItem> items) {
    this.name = name;
    this.items = items;
  }

  public static class HashItem {

    String address;
    String attachmentTimestamp;
    String attachmentTimestampLowerBound;
    String attachmentTimestampUpperBound;
    String branchTransaction;
    String bundle;
    String currentIndex;
    String hash;
    String lastIndex;
    String nonce;
    String obsoleteTag;
    String persistence;
    String signatureFragments;
    String timestamp;
    String trunkTransaction;
    String value;

    public HashItem(String address, String attachmentTimestamp,
                    String attachmentTimestampLowerBound, String attachmentTimestampUpperBound,
                    String branchTransaction, String bundle, String currentIndex, String hash,
                    String lastIndex, String nonce, String obsoleteTag, String persistence,
                    String signatureFragments, String timestamp, String trunkTransaction,
                    String value) {
      this.address = address;
      this.attachmentTimestamp = attachmentTimestamp;
      this.attachmentTimestampLowerBound = attachmentTimestampLowerBound;
      this.attachmentTimestampUpperBound = attachmentTimestampUpperBound;
      this.branchTransaction = branchTransaction;
      this.bundle = bundle;
      this.currentIndex = currentIndex;
      this.hash = hash;
      this.lastIndex = lastIndex;
      this.nonce = nonce;
      this.obsoleteTag = obsoleteTag;
      this.persistence = persistence;
      this.signatureFragments = signatureFragments;
      this.timestamp = timestamp;
      this.trunkTransaction = trunkTransaction;
      this.value = value;
    }


  }
}
