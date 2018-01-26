//
//  RequestManager.swift
//  ExamTest
//
//  Created by Florin Ionita on 1/25/18.
//  Copyright © 2018 mobile. All rights reserved.
//

import UIKit
import Alamofire

struct Parser {
    static let status = "status"
    static let id = "id"
    static let quantity = "quantity"
    static let name = "name"
    static let type = "type"
}

let baseUrl = "http://172.25.12.182:4001"
let gamesURL = "\(baseUrl)/games"
let buyGameURL = "\(baseUrl)/buyGame"
let deleteGameURL = "\(baseUrl)/removeGame"
let rentGameUrl = "\(baseUrl)/rentGame"
let allGamesURL = "\(baseUrl)/all"
let addGameURL = "\(baseUrl)/addGame"
let updateURL = "\(baseUrl)/addGame"

class RequestManager: NSObject {

    static private let shared = RequestManager.init()
    
    static func sharedInstance() -> RequestManager {
        return self.shared
    }
    
    private override init() {
        super.init()
    }
    
    func makeGamesRequest(completion: @escaping (Bool) -> Void) {
        Alamofire.request(gamesURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? [[String: Any]] {
//                    self.makeGamesFromData(data)
                    completion(true)
                }
            case .failure:
                completion(false)
            }
        }
    }
    
//    fileprivate func makeGamesFromData(_ data: [[String: Any]]) {
//        var games: [Game] = []
//
//        for dictionary in data {
//            let id = dictionary[Parser.id] as! Int
//            let status = dictionary[Parser.status] as! String
//            let quantity = dictionary[Parser.quantity] as! Int
//            let name = dictionary[Parser.name] as! String
//            let type = dictionary[Parser.type] as! String
//
//            let game = Game.init(id: id, name: name, quantity: quantity, type: type, status: status)
//            games.append(game)
//        }
//
//        ApplicationManager.sharedInstance().games = games
//    }
    
//    fileprivate func makeAllGamesFromData(_ data: [[String: Any]]) {
//        var games: [Game] = []
//
//        for dictionary in data {
//            let id = dictionary[Parser.id] as! Int
//            let status = dictionary[Parser.status] as! String
//            let quantity = dictionary[Parser.quantity] as! Int
//            let name = dictionary[Parser.name] as! String
//            let type = dictionary[Parser.type] as! String
//
//            let game = Game.init(id: id, name: name, quantity: quantity, type: type, status: status)
//            games.append(game)
//        }
//
//        ApplicationManager.sharedInstance().allGames = games
//    }
    
}
