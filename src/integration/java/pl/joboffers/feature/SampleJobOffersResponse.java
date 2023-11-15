package pl.joboffers.feature;

public interface SampleJobOffersResponse {

    default String bodyWithZeroOffersJson() {
        return "[]";
    }

    default String bodyWithOneOffersJson() {
        return """
                [
                 {
                         "title": "Junior Java Developer",
                         "company": "BlueSoft Sp. z o.o.",
                         "salary": "7 000 – 9 000 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre_one_offer_url"
                     }
                ]
                 """;


    }

    default String bodyWithTwoOffersJson() {
        return """
                [
                 {
                         "title": "Junior Java Developer",
                         "company": "BlueSoft Sp. z o.o.",
                         "salary": "7 000 – 9 000 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre_one_offer_url"
                     },
                     {
                         "title": "Java (CMS) Developer",
                         "company": "Efigence SA",
                         "salary": "16 000 – 18 000 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/java-cms-developer-efigence-warszawa-b4qs8loh_two_offer_url"
                     }
                ]
                 """;
    }

    default String bodyWithThreeOffersJson() {
        return """
                [
                 {
                         "title": "Junior Java Developer",
                         "company": "BlueSoft Sp. z o.o.",
                         "salary": "7 000 – 9 000 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre_one_offer_url"
                     },
                     {
                         "title": "Java (CMS) Developer",
                         "company": "Efigence SA",
                         "salary": "16 000 – 18 000 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/java-cms-developer-efigence-warszawa-b4qs8loh_two_offer_url"
                     },
                     {
                         "title": "Junior Java Developer",
                         "company": "Sollers Consulting",
                         "salary": "7 500 – 11 500 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-sollers-consulting-warszawa-s6et1ucc_three_offer_url"
                     }
                ]
                 """;


    }

    default String bodyWithFourOffersJson() {
        return """
                [
                     {
                         "title": "Junior Java Developer",
                         "company": "BlueSoft Sp. z o.o.",
                         "salary": "7 000 – 9 000 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre_one_offer_url"
                     },
                     {
                         "title": "Java (CMS) Developer",
                         "company": "Efigence SA",
                         "salary": "16 000 – 18 000 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/java-cms-developer-efigence-warszawa-b4qs8loh_two_offer_url"
                     },
                     {
                         "title": "Junior Java Developer",
                         "company": "Sollers Consulting",
                         "salary": "7 500 – 11 500 PLN",
                         "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-sollers-consulting-warszawa-s6et1ucc_three_offer_url"
                     },
                      {
                          "title": "Junior Java Developer",
                          "company": "Sollers Consulting",
                          "salary": "7 500 – 11 500 PLN",
                          "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-sollers-consulting-warszawa-s6et1ucc4"
                      }
                                 ]
                                  """;
    }
}