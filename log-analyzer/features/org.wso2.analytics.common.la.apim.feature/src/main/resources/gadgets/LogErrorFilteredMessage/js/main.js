/*
 * Copyright (c)  2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var serverUrl = "https://"+location.hostname +":"+ location.port + "/admin-dashboard/modules/la/log-analyzer-proxy.jag";
var client = new AnalyticsClient().init(null, null, serverUrl);
var div = "#tblFilteredMessages";
var from;
var to;
var dataM = [];
var initState = true;
var filterdMessage;
var filterdTimestamp;
var filterdTimeframe;

var meta = {
    "names": ["message", "class", "timestamp", "trace"],
    "types": ["ordinal", "ordinal", "ordinal", "ordinal"]
};

var configTable = {
    key: "timestamp",
    title: "FilteredMessages",
    charts: [{
        type: "table",
        columns: ["message", "class", "timestamp", "trace"],
        columnTitles: ["ERROR Message", "Class", "Event Time Stamp", "Error Trace"]
    }
    ],
    width: $(window).width() * 0.95,
    height: $(window).width() * 0.65 > $(window).height() ? $(window).height() : $(window).width() * 0.65,
    padding: {"top": 100, "left": 30, "bottom": 22, "right": 70}
};


function initialize() {
    //fetch();
    $(div).html(getDefaultText());
}

function getDefaultText() {
    return '<div class="status-message">' +
        '<div class="message message-info">' +
        '<h4><i class="icon fw fw-info"></i>No content to display</h4>' +
        '<p>Please select a Log ERROR Message to view details.</p>' +
        '</div>' +
        '</div>';
};

function getEmptyRecordsText() {
    return '<div class="status-message">' +
        '<div class="message message-info">' +
        '<h4><i class="icon fw fw-info"></i>No records found</h4>' +
        '<p>Please select a date range to view stats.</p>' +
        '</div>' +
        '</div>';
}

$(document).ready(function () {
    initialize();
});

function fetch() {
    dataM.length = 0;
    var queryInfo;
    console.log("FilteredMessagesFetching");
    queryInfo = {
        tableName: "LOGANALYZER",
        searchParams: {
            query: "_content: \"" + filterdMessage + "\" AND  _eventTimeStamp: [" + from + " TO " + to + "]",
            start: 0, //starting index of the matching record set
            count: 100 //page size for pagination
        }
    };
    console.log(queryInfo);
    client.search(queryInfo, function (d) {
        var obj = JSON.parse(d["message"]);
        if (d["status"] === "success") {
            for (var i = 0; i < obj.length; i++) {
                dataM.push([obj[i].values._content, obj[i].values._class, new Date(obj[i].values._eventTimeStamp).toUTCString(), obj[i].values._trace]);
            }
            drawLogAPIMArtifactTableChart();

        }
    }, function (error) {
        console.log("error occured: " + error);
    });
}

function drawLogAPIMArtifactTableChart() {
    $("#tblFilteredMessages").empty();
    var table = new vizg(
        [
            {
                "metadata": this.meta,
                "data": dataM
            }
        ],
        configTable
    );
    table.draw(div);
    var table2 = $('#FilteredMessages').DataTable();
    $("#tableChart-FilteredMessages > tr").on("click", function (event) {
        publish({timestamp: new Date(this.getElementsByClassName("timestamp")[0].textContent).getTime()});
    });
}

function publish(data) {
    gadgets.Hub.publish("publisher2", data);
};

function subscribe(callback) {
    gadgets.HubSettings.onConnect = function () {
        gadgets.Hub.subscribe("subscriber2", function (topic, data, subscriber) {
            callback(topic, data, subscriber)
        });
    };
    console.log("APIM subscribed");
}

subscribe(function (topic, data, subscriber) {
    console.log("Received Data :" + data["selected"]);
    filterdMessage = data["selected"].replace(/\"/g, "\\\"");
    filterdTimestamp = data["timeStamp"];
    filterdTimeframe = data["timeFrame"];
    from = new Date(filterdTimestamp);
    to = new Date(filterdTimestamp);
    var duration;
    if (filterdTimeframe === "daily") {
        duration = from.getDate() + 1;
        to.setDate(duration);
    } else if (filterdTimeframe === "monthly") {
        duration = from.getMonth() + 1;
        to.setMonth(duration);
    } else if (filterdTimeframe === "weekly") {
        duration = from.getDate() + 7;
        to.setDate(duration);
    }
    from = from.getTime();
    to = to.getTime();
    fetch();
});
